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
        List<CustomerDTO> customers = customerRepository.entityToDTO(customerRepository.getAll());
        return Response.ok(customers).build();
    }

    @GET
    @Path("/{id}")
    public Response getCustomerById(@PathParam("id") Long id) {
        CustomerDTO customer = customerRepository.entityToDTO(customerRepository.findById(id));
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(customer).build();
    }

    @GET
    @Path("/{id}/orders")
    public Response getCustomerOrders(@PathParam("id") Long id) {
        List<OrderDTO> orders = orderRepository.entityToDTO(orderRepository.findByCustomerId(id));
        return Response.ok(orders).build();
    }

    @POST
    @Transactional
    public Response createCustomer(CustomerDTO customerDto) {
        Customer customer = customerRepository.dtoToEntity(customerDto);
        customerRepository.persist(customer);
        return Response.status(Response.Status.CREATED).entity(customerDto).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateCustomer(@PathParam("id") Long id, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(id);

        if (existingCustomer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        existingCustomer.setFirstName(customerDTO.firstName());
        existingCustomer.setLastName(customerDTO.lastName());
        existingCustomer.setEmail(customerDTO.email());
        existingCustomer.setPhoneNumber(customerDTO.phoneNumber());
        Customer updated = customerRepository.update(existingCustomer);

        return Response.ok(customerRepository.entityToDTO(updated)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteCustomer(@PathParam("id") Long id) {
        CustomerDTO customer = customerRepository.entityToDTO(customerRepository.findById(id));
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        customerRepository.deleteById(id);
        return Response.noContent().build();
    }
}