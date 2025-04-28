package ac.htl.leonding.boundary;

import ac.htl.leonding.control.OrderItemRepository;
import ac.htl.leonding.control.OrderRepository;
import ac.htl.leonding.entities.Order;
import ac.htl.leonding.entities.OrderItem;
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

    @Inject
    OrderItemRepository orderItemRepository;

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

    @GET
    @Path("/{id}/items")
    public List<OrderItem> getOrderItems(@PathParam("id") Long id) {
        return orderItemRepository.findByOrderId(id);
    }

    @POST
    @Path("/{id}/items")
    @Transactional
    public Response addItemToOrder(@PathParam("id") Long id, OrderItem item) {
        Order order = orderRepository.findById(id);
        if (order == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        item.setOrder(order);
        orderItemRepository.persist(item);

        return Response.status(Response.Status.CREATED).entity(item).build();
    }


//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response addOrder(Order order) {
//        return Response.ok(
//                orderRepository.addOrder(order))
//                .build();
//    }
//
//    @GET
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response findOrderById(@PathParam("id") Long id) {
//        return Response.ok(
//                orderRepository.findOrderById(id))
//                .build();
//    }
//
//    @PUT
//    @Path("/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response updateOrder(@PathParam("id") Long id, Order order) {
//        return Response.ok(
//                orderRepository.updateOrder(id, order))
//                .build();
//    }
//
//    @DELETE
//    @Path("/{id}")
//    public Response deleteOrder(@PathParam("id") Long id) {
//        orderRepository.deleteOrder(id);
//        return Response.noContent().build();
//    }
}
