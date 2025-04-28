package ac.htl.leonding.boundary;

import ac.htl.leonding.control.OrderRepository;
import ac.htl.leonding.entities.Order;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject
    OrderRepository orderRepository;

    @GET
    public Response getAllOrders() {
        List<Order> orders = orderRepository.getAll();
        return Response.ok(orders).build();
    }

    @GET
    @Path("/{id}")
    public Response getOrderById(@PathParam("id") Long id) {
        Order order = orderRepository.findById(id);
        if (order == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(order).build();
    }

    @GET
    @Path("/customer/{customerId}")
    public Response getOrdersByCustomerId(@PathParam("customerId") Long customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        return Response.ok(orders).build();
    }

    @GET
    @Path("/restaurant/{restaurantId}")
    public Response getOrdersByRestaurantId(@PathParam("restaurantId") Long restaurantId) {
        List<Order> orders = orderRepository.findByRestaurantId(restaurantId);
        return Response.ok(orders).build();
    }

    @GET
    @Path("/status/{status}")
    public Response getOrdersByStatus(@PathParam("status") String status) {
        List<Order> orders = orderRepository.findByStatus(status);
        return Response.ok(orders).build();
    }

    @POST
    @Transactional
    public Response createOrder(Order order) {
        orderRepository.save(order);
        return Response.status(Response.Status.CREATED).entity(order).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateOrder(@PathParam("id") Long id, Order order) {
        Order existingOrder = orderRepository.findById(id);
        if (existingOrder == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        order.setId(id);
        Order updated = orderRepository.update(order);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteOrder(@PathParam("id") Long id) {
        Order order = orderRepository.findById(id);
        if (order == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        orderRepository.deleteById(id);
        return Response.noContent().build();
    }
}