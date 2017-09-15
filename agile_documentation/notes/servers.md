# Notes serveurs

## Mise en place du load balancer

### Tentative 1 - cluster

[TODO : écrire les notes]

### Tentative 2 - un serveur par machine

#### Le load-balancer

On utilise `standalone-load-balancer.xml`, un profil dédié au load-balancer inclus depuis WildFly 10.1 dans les
exemples. Ce fichier est donné lors du lancement du serveur, qui se fait via le script suivant :

```bash
#!/bin/bash

# Clean up
pgrep -d " " -f "wildfly" | xargs kill;

# Config variables
MY_IP=$(ip route get 8.8.8.8 | awk '{print $NF; exit}')
WILDFLY_ARCHIVE=path/to/wildfly-10.1.0.Final.tar.gz
WILDFLY_HOME=path/to/wildfly
DEPLOY_FILE=~/path/to/file.war

# Fresh install
rm -rf $WILDFLY_HOME
tar -zxvf $WILDFLY_ARCHIVE
mv wildfly-10.1.0.Final $WILDFLY_HOME

# Standalone mode + deployment
cp $WILDFLY_HOME/docs/examples/configs/standalone-load-balancer.xml $WILDFLY_HOME/standalone/configuration/
cp $DEPLOY_FILE $WILDFLY_HOME/standalone/deployments

# Start the load balancer with the load-balancer profile
$WILDFLY_HOME/bin/standalone.sh -c standalone-load-balancer.xml -b $MY_IP -bprivate $MY_IP -bmanagement $MY_IP
```

#### Les workers

On utilise `standalone-ha.xml`, un profil qui a le composant modcluster inclus. Ce fichier est donné lors du lancement
du serveur, qui se fait via le script suivant :

```bash
#!/bin/bash

# Clean up
pgrep -d " " -f "wildfly" | xargs kill;

# Config variables
MY_IP=$(ip route get 8.8.8.8 | awk '{print $NF; exit}')
WILDFLY_ARCHIVE=path/to/wildfly-10.1.0.Final.tar.gz
WILDFLY_HOME=path/to/wildfly
DEPLOY_FILE=~/path/to/file.war

# Fresh install
rm -rf $WILDFLY_HOME
tar -zxvf $WILDFLY_ARCHIVE
mv wildfly-10.1.0.Final $WILDFLY_HOME

# Deployment
cp $DEPLOY_FILE $WILDFLY_HOME/standalone/deployments

# Start the node worker with the standalone-ha profile
$WILDFLY_HOME/bin/standalone.sh -c standalone-ha.xml -b $MY_IP -bprivate $MY_IP -bmanagement $MY_IP
```

### Tentative 3 - Un load-balancer avec des clusters

Pas encore effectué.

## Faire communiquer les EJBs avec la base de données

### Tentative 1 - Hibernate uniquement

L'idée était d'avoir un fichier `persistence.xml` ressemblant au suivant (données volontairement éronnées) :

```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://java.sun.com/xml/ns/persistence" version="2.0">

    <persistence-unit name="pokemonDB">
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
        <class>...</class>
        <properties>
            <!--Database -->
            <property name="hibernate.connection.url" value="jdbc:postgresql://175.78.54.10:5432/pokemonDB"/>
            <property name="hibernate.connection.driver_class" value="org.postgresql.Driver"/>
            <property name="hibernate.connection.username" value="user"/>
            <property name="hibernate.connection.password" value="password"/>

            <!-- SQL -->
            <property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQLDialect"/>
            <property name="hibernate.show_sql" value="true"/>
            <property name="hibernate.format_sql" value="true"/>

            <!-- Others -->
            <property name="hibernate.archive.autodetection" value="class"/>
            <property name="hbm2ddl.auto" value="update"/>
        </properties>
    </persistence-unit>
</persistence>
```

Avec les bonnes dépendances ajoutées grâce à maven (par exemple la dépendance au driver postgres). Après un nombre
d'essais incalculable, impossible d'établir une connexion, le même message d'erreur apparaissant toujours (se
résumant à des tables non trouvées). Il semblerait que malgré la prise en compte du fichier `persistence.xml` par le
serveur wildfly lors du déploiement, celui-ci essayait toujours de se connecter à une base de données H2 - celle
présente par défaut dans le fichier de configuration serveur `standalone.xml`. Est venue une seconde idée : utiliser
ce fichier de configuration pour renseigner la base de données postgres.

### Tentative 2 - ajouter la BD en tant que datasource et utiliser le jndi

Modification du fichier standalone.xml (données volontairement éronnées) :

```xml
    <subsystem xmlns="urn:jboss:domain:datasources:4.0">
        <datasources>
            <datasource jta="true" jdni-name="java:jboss/datasources/pokemonDB" pool-name="pokemonDB" enabled="true" use-ccm="true">
                <connection-url>jdbc:postgresql://175.78.54.10:5432/pokemonDB</connection-url>
                <driver-class>org.postgresql.Driver</driver-class>
                <driver>web.war_org.postgresql.Driver_42_1</driver>
                <security>
                    <user-name>user</user-name>
                    <password>password</password>
                </security>
                <pool>
                    <min-pool-size>5</min-pool-size>
                    <initial-pool-size>5</initial-pool-size>
                    <max-pool-size>100</max-pool-size>
                    <prefill>true</prefill>
                </pool>
                <validation>
                    <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChcker"/>
                    <background-validation>true</background-validation>
                    <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter"/>
                </validation>
            </datasource>
        </datasources>
    </subsystem>
```

Modification du fichier persistence.xml :

```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.1"
   xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xsi:schemaLocation="
        http://xmlns.jcp.org/xml/ns/persistence
        http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd">

    <persistence-unit name="pokemonDB" transaction-type="JTA">
        <jta-data-source>java:jboss/datasources/pokemonDB</jta-data-source>
        <class>...</class>
        <properties>
            <property name="hibernate.show_sql" value="true"/>
            <property name="hibernate.format_sql" value="true"/>
            <property name="hibernate.archive.autodetection" value="class"/>
            <property name="hbm2ddl.auto" value="update"/>
      </properties>
    </persistence-unit>
</persistence>
```

Qui fonctionne.