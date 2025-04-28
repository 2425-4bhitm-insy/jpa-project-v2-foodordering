package ac.htl.leonding.boundary;

import ac.htl.leonding.control.CustomerRepository;
import ac.htl.leonding.entities.Customer;
import ac.htl.leonding.entities.Order;
import ac.htl.leonding.entities.User;
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

    @GET
    public List<Customer> getAllCustomers() {
        return customerRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getCustomerById(@PathParam("id") Long id) {
        Customer customer = customerRepository.findById(id);
        if (customer != null) {
            return Response.ok(customer).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Transactional
    public Response createCustomer(Customer customer) {
        customerRepository.persist(customer);
        return Response.status(Response.Status.CREATED).entity(customer).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateCustomer(@PathParam("id") Long id, Customer customer) {
        User entity = customerRepository.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        entity.setFirstName(customer.getFirstName());
        entity.setLastName(customer.getLastName());
        entity.setEmail(customer.getEmail());
        entity.setAddress(customer.getAddress());
        entity.setPhoneNumber(customer.getPhoneNumber());

        return Response.ok(entity).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteCustomer(@PathParam("id") Long id) {
       customerRepository.deleteById(id);

        if (customerRepository.findById(id) == null) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}/orders")
    public Response getCustomerOrders(@PathParam("id") Long id) {
        List<Order> orders = customerRepository.findAllOrders(id);
        return Response.ok(orders).build();
    }
}
