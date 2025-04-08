package ac.htl.leonding.boundary;

import ac.htl.leonding.control.OrderRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/orders")
public class OrderResource {

    @Inject
    OrderRepository orderRepository;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTemp() {
        return "This is a temporary method!";
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
