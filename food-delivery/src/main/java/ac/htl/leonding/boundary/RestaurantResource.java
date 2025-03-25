package ac.htl.leonding.boundary;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/restaurants")
public class RestaurantResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTemp() {
        return "This is a temporary method!";
    }
}
