package ac.htl.leonding.boundary;

import ac.htl.leonding.control.CustomerRepository;

import ac.htl.leonding.control.OrderRepository;
import ac.htl.leonding.entities.Customer;
import ac.htl.leonding.entities.Order;
import ac.htl.leonding.entities.dto.CustomerDTO;
import ac.htl.leonding.entities.dto.OrderDTO;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @Inject
    CustomerRepository customerRepository;

    @Inject
    OrderRepository orderRepository;

    @GET
    public Response getAllCustomers() {
        List<Customer> customers = customerRepository.listAll();
        List<CustomerDTO> customerDTOs = customerRepository.entityToDTO(customers);
        return Response.ok(customerDTOs).build();
    }

    @GET
    @Path("/{id}")
    public Response getCustomerById(@PathParam("id") Long id) {
        Customer customer = customerRepository.findById(id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        CustomerDTO customerDTO = customerRepository.entityToDTO(customer);
        return Response.ok(customerDTO).build();
    }

    @GET
    @Path("/{id}/orders")
    public Response getCustomerOrders(@PathParam("id") Long id) {
        List<Order> orders = customerRepository.findAllOrders(id);
        // Hier benötigen wir die OrderDTO-Methode, entweder aus dem OrderRepository oder hier inline
        List<OrderDTO> orderDTOs = orderRepository.entityToDTO(orders);
        return Response.ok(orderDTOs).build();
    }

    @POST
    @Transactional
    public Response createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerRepository.dtoToEntity(customerDTO);
        customerRepository.persist(customer);
        return Response.status(Response.Status.CREATED).entity(customerRepository.entityToDTO(customer)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateCustomer(@PathParam("id") Long id, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(id);
        if (existingCustomer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Customer customer = customerRepository.dtoToEntity(customerDTO);
        customer.setId(id);
        Customer updated = customerRepository.update(customer);
        return Response.ok(customerRepository.entityToDTO(updated)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteCustomer(@PathParam("id") Long id) {
        Customer customer = customerRepository.findById(id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        customerRepository.deleteById(id);
        return Response.noContent().build();
    }
}