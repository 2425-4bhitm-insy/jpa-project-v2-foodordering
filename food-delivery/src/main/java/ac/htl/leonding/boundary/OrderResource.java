package ac.htl.leonding.boundary;//package ac.htl.leonding.boundary;

import ac.htl.leonding.control.OrderRepository;
import ac.htl.leonding.entities.Order;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject
    OrderRepository orderRepository;



    @GET
    public List<Order> getAllOrders() {
        return orderRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getOrderById(@PathParam("id") Long id) {
        Order order = orderRepository.findById(id);
        if (order != null) {
            return Response.ok(order).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Transactional
    public Response createOrder(Order order) {
        orderRepository.persist(order);
        return Response.status(Response.Status.CREATED).entity(order).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateOrder(@PathParam("id") Long id, Order order) {
        Order entity = orderRepository.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        entity.setDeliveryAddress(order.getDeliveryAddress());
        entity.setOrderDate(order.getOrderDate());
        entity.setStatus(order.getStatus());
        entity.setTotalPrice(order.getTotalPrice());

        return Response.ok(entity).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteOrder(@PathParam("id") Long id) {
        boolean deleted = orderRepository.deleteById(id);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }






}
