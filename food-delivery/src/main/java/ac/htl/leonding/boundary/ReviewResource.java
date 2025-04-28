package ac.htl.leonding.boundary;//package ac.htl.leonding.boundary;

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
    public List<Review> getAllReviews() {
        return reviewRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getReviewById(@PathParam("id") Long id) {
        Review review = reviewRepository.findById(id);
        if (review != null) {
            return Response.ok(review).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Transactional
    public Response createReview(Review review) {
        reviewRepository.persist(review);
        return Response.status(Response.Status.CREATED).entity(review).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateReview(@PathParam("id") Long id, Review review) {
        Review entity = reviewRepository.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        entity.setRating(review.getRating());

        return Response.ok(entity).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteReview(@PathParam("id") Long id) {
        boolean deleted = reviewRepository.deleteById(id);
        if (deleted) {return Response.noContent().build();
        }
      return Response.status(Response.Status.NOT_FOUND).build();
   }
}
