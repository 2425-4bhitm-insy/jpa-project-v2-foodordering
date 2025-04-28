package ac.htl.leonding.boundary;

import ac.htl.leonding.control.CustomerRepository;
import ac.htl.leonding.entities.Customer;
import ac.htl.leonding.entities.Order;
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
    public Response getAllCustomers() {
        List<Customer> customers = customerRepository.listAll();
        return Response.ok(customers).build();
    }

    @GET
    @Path("/{id}")
    public Response getCustomerById(@PathParam("id") Long id) {
        Customer customer = customerRepository.findById(id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(customer).build();
    }

    @GET
    @Path("/{id}/orders")
    public Response getCustomerOrders(@PathParam("id") Long id) {
        List<Order> orders = customerRepository.findAllOrders(id);
        return Response.ok(orders).build();
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
        Customer existingCustomer = customerRepository.findById(id);
        if (existingCustomer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        customer.setId(id);
        Customer updated = customerRepository.update(customer);
        return Response.ok(updated).build();
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