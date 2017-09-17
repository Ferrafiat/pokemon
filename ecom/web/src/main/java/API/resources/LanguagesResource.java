package API.resources;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("languages")
public class LanguagesResource extends javax.ws.rs.core.Application {
    @PersistenceContext
    EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLanguages() {
        String query = "SELECT l FROM LanguagesEntity l";
        return Response.status(Response.Status.OK).entity(entityManager.createQuery(query).getResultList()).build();
    }
}
