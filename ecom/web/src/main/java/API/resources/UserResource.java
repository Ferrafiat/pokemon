package API.resources;

import API.resources.beans.UserInputSignup;
import API.resources.beans.UserLog;
import Services.UserService;
import database.entities.UsersEntity;

import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("user")
public class UserResource {
    @PersistenceContext
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<UsersEntity> getUsers() {
        Query users = entityManager.createQuery("SELECT U FROM UsersEntity U GROUP BY U.id");
        return (Collection<UsersEntity>) users.getResultList();
    }

    @Path("signup")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/json")
    public UsersEntity createUser(UserInputSignup input) {
        entityTransaction = entityManager.getTransaction();
        if (UserService.samePaswword(input.password, input.password_verify)) {
            UsersEntity newUser = new UsersEntity(
                    input.firstname,
                    input.birthday,
                    input.gender,
                    input.email,
                    input.password,
                    input.lastname
            );

            try {
                entityTransaction.begin();
                entityManager.persist(newUser);
                entityTransaction.commit();
            } catch (Exception e) {
                if (entityTransaction != null) {
                    entityTransaction.rollback();
                }
            } finally {
                entityManager.close();
            }
        }

        return newUser;
    }

    @Path("login")
    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Object login(final UserLog input) {
        UsersEntity usersEntity = null;

        try {
            Query userTryLogin =
                    entityManager.createQuery(
                            "SELECT U FROM UsersEntity U WHERE U.email = :email and U.password = :password");
            usersEntity = (UsersEntity) userTryLogin
                    .setParameter("email", input.email)
                    .setParameter("password", input.password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return "No result";
        } finally {
            return usersEntity;
        }
    }

    @Path("account/{handle}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object displayUserAccount(@PathParam("handle") String handle) {
        UsersEntity usersEntity = null;

        try {
            Query user =
                    entityManager.createQuery(
                            "SELECT U FROM UsersEntity U WHERE U.handle = :handle");
            usersEntity =
                    (UsersEntity) user
                            .setParameter("handle", handle)
                            .getSingleResult();
        } catch (NoResultException e) {
            return "No result";
        } finally {
            return usersEntity;
        }
    }
}
