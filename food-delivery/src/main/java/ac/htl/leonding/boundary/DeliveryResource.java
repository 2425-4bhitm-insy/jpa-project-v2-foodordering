package ac.htl.leonding.boundary;

import ac.htl.leonding.control.DeliveryRepository;
import ac.htl.leonding.entities.Delivery;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;


import java.util.List;

@Path("/deliveries")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeliveryResource {

    @Inject
    DeliveryRepository deliveryRepository;


    @GET
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getDeliveryById(@PathParam("id") Long id) {
        Delivery delivery = deliveryRepository.findById(id);
        if (delivery != null) {
            return Response.ok(delivery).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Transactional
    public Response createDelivery(Delivery delivery) {
        deliveryRepository.persist(delivery);
        return Response.status(Response.Status.CREATED).entity(delivery).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateDelivery(@PathParam("id") Long id, Delivery delivery) {
        Delivery entity = deliveryRepository.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        entity.setEstimatedTime(delivery.getEstimatedTime());
        entity.setStatus(delivery.getStatus());

        return Response.ok(entity).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteDelivery(@PathParam("id") Long id) {
        boolean deleted = deliveryRepository.deleteById(id);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/{id}/status")
    @Transactional
    public Response updateDeliveryStatus(@PathParam("id") Long id, String status) {
        Delivery delivery = deliveryRepository.findById(id);
        if (delivery == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        delivery.setStatus(status);
        return Response.ok(delivery).build();
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

