package ac.htl.leonding.control;



import ac.htl.leonding.entities.Customer;
import ac.htl.leonding.entities.Order;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class CustomerRepository implements PanacheRepository<Customer> {

    public List<Customer> listAll() {
        return listAll();
    }

    public List<Order> findAllOrders(Long customerId) {
        Customer customer = findById(customerId);
        return customer != null ? customer.getOrders() : List.of();
    }

    public Customer findById(Long id) {
        return find("id", id).firstResult();
    }

    public Customer update(Customer customer) {

    }
}