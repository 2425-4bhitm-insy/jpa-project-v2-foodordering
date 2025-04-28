package ac.htl.leonding.boundary;

import ac.htl.leonding.control.RestaurantRepository;
import ac.htl.leonding.control.ReviewRepository;
import ac.htl.leonding.entities.Restaurant;
import ac.htl.leonding.entities.Review;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("/restaurants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestaurantResource {

    @Inject
    RestaurantRepository restaurantRepository;

    @Inject
    ReviewRepository reviewRepository;

    @GET
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getRestaurantById(@PathParam("id") Long id) {
        Restaurant restaurant = restaurantRepository.findById(id);
        if (restaurant != null) {
            return Response.ok(restaurant).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Transactional
    public Response createRestaurant(Restaurant restaurant) {
        restaurantRepository.persist(restaurant);
        return Response.status(Response.Status.CREATED).entity(restaurant).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateRestaurant(@PathParam("id") Long id, Restaurant restaurant) {
        Restaurant entity = restaurantRepository.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        entity.setName(restaurant.getName());
        entity.setAddress(restaurant.getAddress());
        entity.setDescription(restaurant.getDescription());
        entity.setRating(restaurant.getRating());

        return Response.ok(entity).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteRestaurant(@PathParam("id") Long id) {
        boolean deleted = restaurantRepository.deleteById(id);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}/reviews")
    public List<Review> getRestaurantReviews(@PathParam("id") Long id) {
        return reviewRepository.findByRestaurantId(id);
    }

    @GET
    @Path("/search")
    public List<Restaurant> searchRestaurants(@QueryParam("name") String name) {
        if (name != null && !name.isEmpty()) {
            return restaurantRepository.findByName(name);
        }
        return restaurantRepository.listAll();
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
