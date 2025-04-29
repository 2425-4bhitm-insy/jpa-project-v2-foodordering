package ac.htl.leonding.boundary;

import ac.htl.leonding.control.CustomerRepository;
import ac.htl.leonding.control.DeliveryRepository;
import ac.htl.leonding.control.OrderRepository;
import ac.htl.leonding.control.RestaurantRepository;
import ac.htl.leonding.entities.Order;
import ac.htl.leonding.entities.dto.OrderDTO;
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

    @Inject
    CustomerRepository customerRepository;

    @Inject
    RestaurantRepository restaurantRepository;

    @Inject
    DeliveryRepository deliveryRepository;

    @GET
    public Response getAllOrders() {
        List<OrderDTO> orders = orderRepository.entityToDTO(orderRepository.getAll());
        return Response.ok(orders).build();
    }

    @GET
    @Path("/{id}")
    public Response getOrderById(@PathParam("id") Long id) {
        OrderDTO order = orderRepository.entityToDTO(orderRepository.findById(id));
        if (order == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(order).build();
    }

    @GET
    @Path("/customer/{customerId}")
    public Response getOrdersByCustomerId(@PathParam("customerId") Long customerId) {
        List<OrderDTO> orders = orderRepository.entityToDTO(orderRepository.findByCustomerId(customerId));
        return Response.ok(orders).build();
    }

    @GET
    @Path("/restaurant/{restaurantId}")
    public Response getOrdersByRestaurantId(@PathParam("restaurantId") Long restaurantId) {
        List<OrderDTO> orders = orderRepository.entityToDTO(orderRepository.findByRestaurantId(restaurantId));
        return Response.ok(orders).build();
    }

    @GET
    @Path("/status/{status}")
    public Response getOrdersByStatus(@PathParam("status") String status) {
        List<OrderDTO> orders = orderRepository.entityToDTO(orderRepository.findByStatus(status));
        return Response.ok(orders).build();
    }

    @POST
    @Transactional
    public Response createOrder(OrderDTO orderDto) {

        Order order = orderRepository.dtoToEntity(orderDto);
        orderRepository.save(order);
        return Response.status(Response.Status.CREATED).entity(orderDto).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateOrder(@PathParam("id") Long id, OrderDTO orderDTO) {
        Order existingOrder = orderRepository.findById(id);
        if (existingOrder == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        existingOrder.setOrderDate(orderDTO.orderDate());
        existingOrder.setDeliveryAddress(orderDTO.deliveryAddress());
        existingOrder.setTotalPrice(orderDTO.totalPrice());
        existingOrder.setStatus(orderDTO.status());
        existingOrder.setCustomer(customerRepository.findById(orderDTO.customerId()));
        existingOrder.setRestaurant(restaurantRepository.findById(orderDTO.restaurantId()));

        Order updated = orderRepository.update(existingOrder);

        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteOrder(@PathParam("id") Long id) {
        OrderDTO order = orderRepository.entityToDTO(orderRepository.findById(id));
        if (order == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        deliveryRepository.findByDeliveryPersonId(order.customerId());

        orderRepository.deleteById(id);
        return Response.noContent().build();
    }


}