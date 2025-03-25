package ac.htl.leonding.boundary;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/restaurants")
public class RestaurantResource {

//    @Inject
//    RestaurantRepository restaurantRepository;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTemp() {
        return "This is a temporary method!";
    }

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response addRestaurant(Restaurant restaurant) {
//        return Response.ok(
//                restaurantRepository.addRestaurant(restaurant))
//                .build();
//    }
//
//    @GET
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response findRestaurantById(@PathParam("id") Long id) {
//        return Response.ok(
//                restaurantRepository.findRestaurantById(id))
//                .build();
//    }
}
