package ac.htl.leonding.boundary;

import ac.htl.leonding.control.DeliveryRepository;
import ac.htl.leonding.entities.Delivery;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("/deliveries")
public class DeliveryResource {

    @Inject
    DeliveryRepository deliveryRepository;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTemp() {
        return "This is a temporary method!";

    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDeliveries() {
        deliveryRepository.findAll();
        deliveryRepository.getAll();

        return Response.ok(deliveryRepository.getAll()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    @Transactional
    public Response addDelivery(Delivery delivery) {


       deliveryRepository.save(delivery);

       return Response.ok(delivery).build();
    }

    @GET
    @Path("/{id}")
    public Response findDeliveryById(@PathParam("id") Long id) {

        return Response.ok(deliveryRepository.findDeliveryById(id)).build();
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

