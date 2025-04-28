package ac.htl.leonding.boundary;

import ac.htl.leonding.control.ReviewRepository;
import ac.htl.leonding.entities.Review;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReviewResource {

    @Inject
    ReviewRepository reviewRepository;

    @GET
    public Response getAllReviews() {
        List<Review> reviews = reviewRepository.getAll();
        return Response.ok(reviews).build();
    }

    @GET
    @Path("/{id}")
    public Response getReviewById(@PathParam("id") Long id) {
        Review review = reviewRepository.findById(id);
        if (review == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(review).build();
    }

    @GET
    @Path("/restaurant/{restaurantId}")
    public Response getReviewsByRestaurantId(@PathParam("restaurantId") Long restaurantId) {
        List<Review> reviews = reviewRepository.findByRestaurantId(restaurantId);
        return Response.ok(reviews).build();
    }

    @GET
    @Path("/customer/{customerId}")
    public Response getReviewsByCustomerId(@PathParam("customerId") Long customerId) {
        List<Review> reviews = reviewRepository.findByCustomerId(customerId);
        return Response.ok(reviews).build();
    }

    @GET
    @Path("/restaurant/{restaurantId}/average")
    public Response getAverageRatingForRestaurant(@PathParam("restaurantId") Long restaurantId) {
        Double averageRating = reviewRepository.getAverageRatingForRestaurant(restaurantId);
        return Response.ok(averageRating).build();
    }

    @POST
    @Transactional
    public Response createReview(Review review) {
        reviewRepository.save(review);
        return Response.status(Response.Status.CREATED).entity(review).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateReview(@PathParam("id") Long id, Review review) {
        Review existingReview = reviewRepository.findById(id);
        if (existingReview == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        review.setId(id);
        Review updated = reviewRepository.update(review);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteReview(@PathParam("id") Long id) {
        Review review = reviewRepository.findById(id);
        if (review == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        reviewRepository.deleteById(id);
        return Response.noContent().build();
    }
}