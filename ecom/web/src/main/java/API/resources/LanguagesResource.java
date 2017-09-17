package API.resources;

import database.entities.LanguagesEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("languages")
public class LanguagesResource extends javax.ws.rs.core.Application {
    @PersistenceContext
    EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LanguagesEntity> getLanguages() {
        return entityManager.createQuery("SELECT name FROM LanguagesEntity").getResultList();
    }
}
