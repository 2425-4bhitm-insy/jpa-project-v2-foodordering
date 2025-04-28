package ac.htl.leonding.boundary;

import ac.htl.leonding.control.OrderItemRepository;
import ac.htl.leonding.entities.OrderItem;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/order-items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderItemResource {

    @Inject
    OrderItemRepository orderItemRepository;

    @GET
    public Response getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.listAll();
        return Response.ok(orderItems).build();
    }

    @GET
    @Path("/{id}")
    public Response getOrderItemById(@PathParam("id") Long id) {
        OrderItem orderItem = orderItemRepository.findById(id);
        if (orderItem == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(orderItem).build();
    }

    @GET
    @Path("/order/{orderId}")
    public Response getOrderItemsByOrderId(@PathParam("orderId") Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);
        return Response.ok(orderItems).build();
    }

    @GET
    @Path("/dish/{dishId}")
    public Response getOrderItemsByDishId(@PathParam("dishId") Long dishId) {
        List<OrderItem> orderItems = orderItemRepository.findByDishId(dishId);
        return Response.ok(orderItems).build();
    }

    @GET
    @Path("/popular-dishes")
    public Response getMostPopularDishes() {
        List<Object[]> popularDishes = orderItemRepository.findMostPopularDishesPerRestaurant();
        return Response.ok(popularDishes).build();
    }

    @POST
    @Transactional
    public Response createOrderItem(OrderItem orderItem) {
        orderItemRepository.persist(orderItem);
        return Response.status(Response.Status.CREATED).entity(orderItem).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateOrderItem(@PathParam("id") Long id, OrderItem orderItem) {
        OrderItem existing = orderItemRepository.findById(id);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        orderItem.setId(id);
        OrderItem updated = orderItemRepository.update(orderItem);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteOrderItem(@PathParam("id") Long id) {
        boolean deleted = orderItemRepository.deleteById(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}