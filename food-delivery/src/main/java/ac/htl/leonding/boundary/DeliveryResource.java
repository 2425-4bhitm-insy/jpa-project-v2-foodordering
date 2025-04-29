package ac.htl.leonding.boundary;

import ac.htl.leonding.control.DeliveryRepository;
import ac.htl.leonding.entities.Delivery;
import ac.htl.leonding.entities.dto.DeliveryDTO;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/deliveries")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeliveryResource {

    @Inject
    DeliveryRepository deliveryRepository;

    @GET
    public Response getAllDeliveries() {
        List<Delivery> deliveries = deliveryRepository.listAll();
        return Response.ok(deliveries).build();
    }

    @GET
    @Path("/{id}")
    public Response getDeliveryById(@PathParam("id") Long id) {
        Delivery delivery = deliveryRepository.findById(id);
        if (delivery == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(delivery).build();
    }

    @GET
    @Path("/order/{orderId}")
    public Response getDeliveryByOrderId(@PathParam("orderId") Long orderId) {
        Delivery delivery = deliveryRepository.findByOrderId(orderId);
        if (delivery == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(delivery).build();
    }

    @GET
    @Path("/person/{personId}")
    public Response getDeliveriesByPersonId(@PathParam("personId") Long personId) {
        List<Delivery> deliveries = deliveryRepository.findByDeliveryPersonId(personId);
        return Response.ok(deliveries).build();
    }

    @GET
    @Path("/status/{status}")
    public Response getDeliveriesByStatus(@PathParam("status") String status) {
        List<Delivery> deliveries = deliveryRepository.findByStatus(status);
        List<DeliveryDTO> deliveryDTOS = deliveryRepository.entityToDTO(deliveries);
        return Response.ok(deliveryDTOS).build();
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
        Delivery existingDelivery = deliveryRepository.findById(id);
        if (existingDelivery == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        delivery.setId(id);
        Delivery updated = deliveryRepository.update(delivery);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteDelivery(@PathParam("id") Long id) {
        boolean deleted = deliveryRepository.deleteById(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
