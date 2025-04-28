package ac.htl.leonding.boundary;

import ac.htl.leonding.control.RestaurantRepository;
import ac.htl.leonding.entities.Restaurant;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/restaurants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestaurantResource {

    @Inject
    RestaurantRepository restaurantRepository;

    @GET
    public Response getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAllRestaurants();
        return Response.ok(restaurants).build();
    }

    @GET
    @Path("/{id}")
    public Response getRestaurantById(@PathParam("id") Long id) {
        Restaurant restaurant = restaurantRepository.findById(id);
        if (restaurant == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(restaurant).build();
    }

    @GET
    @Path("/rating-above/{minRating}")
    public Response getRestaurantsWithRatingAbove(@PathParam("minRating") Double minRating) {
        List<Restaurant> restaurants = restaurantRepository.findByRatingAbove(minRating);
        return Response.ok(restaurants).build();
    }

    @GET
    @Path("/high-rated")
    public Response getHighRatedRestaurants() {
        List<Object[]> restaurantsWithAvgRating = restaurantRepository.findRestaurantsWithHighAverageRating();
        return Response.ok(restaurantsWithAvgRating).build();
    }

    @GET
    @Path("/search")
    public Response searchRestaurantsByName(@QueryParam("name") String name) {
        List<Restaurant> restaurants = restaurantRepository.findByName(name);
        return Response.ok(restaurants).build();
    }

    @GET
    @Path("/rating/{minRating}")
    public Response getRestaurantsByMinRating(@PathParam("minRating") String minRating) {
        List<Restaurant> restaurants = restaurantRepository.findByRating(minRating);
        return Response.ok(restaurants).build();
    }

    @POST
    @Transactional
    public Response createRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        return Response.status(Response.Status.CREATED).entity(restaurant).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateRestaurant(@PathParam("id") Long id, Restaurant restaurant) {
        Restaurant existingRestaurant = restaurantRepository.findById(id);
        if (existingRestaurant == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        restaurant.setId(id);
        Restaurant updated = restaurantRepository.update(restaurant);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteRestaurant(@PathParam("id") Long id) {
        Restaurant restaurant = restaurantRepository.findById(id);
        if (restaurant == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        restaurantRepository.deleteById(id);
        return Response.noContent().build();
    }
}
