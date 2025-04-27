package ac.htl.leonding.control;

import ac.htl.leonding.entities.Delivery;
import ac.htl.leonding.entities.Order;
import ac.htl.leonding.entities.OrderItem;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;




@ApplicationScoped
public class OrderRepository implements PanacheRepository<Order> {

    public List<Order> getAll() {
        return findAll().list();
    }


    public List<Order> findByCustomerId(Long customerId) {
        return list("customer.id", customerId);
    }

    public List<Order> findByRestaurantId(Long restaurantId) {
        return list("restaurant.id", restaurantId);
    }

    public List<Order> findByStatus(String status) {
        return list("status", status);
    }

    public void save(Order order){
        persist(order);
    }

    public void delete(Order order){
        delete(order);
    }

    public void update(Order order){
        update(order);
    }
}