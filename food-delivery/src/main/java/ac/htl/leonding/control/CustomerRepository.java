package ac.htl.leonding.control;



import ac.htl.leonding.entities.Customer;
import ac.htl.leonding.entities.Order;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {

    public List<Order> findAllOrders(Long customerId) {
        Customer customer = findById(customerId);
        return customer != null ? customer.getOrders() : List.of();
    }
}