package ac.htl.leonding.boundary;

import ac.htl.leonding.control.DeliveryRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/deliveries")
public class DeliveryResource {

    @Inject
    DeliveryRepository deliveryRepository;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTemp() {
        return "This is a temporary method!";

    }

//    @POST
//   @Path("/{id}/status")
//    @Consumes(MediaType.APPLICATION_JSON)
//   public Response updateDeliveryStatus(@PathParam("id") Long id, DeliveryStatus status) {
//        return Response.ok(
//                deliveryRepository.findDeliveryById(id).updateDeliveryStatus(status))
//                .build();
//    }
//
}

