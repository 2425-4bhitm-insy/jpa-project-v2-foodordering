package ac.htl.leonding.control;

import ac.htl.leonding.entities.OrderItem;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class OrderItemRepository implements PanacheRepository<OrderItem> {

    public List<OrderItem> findByOrderId(Long orderId) {
        return list("order.id", orderId);
    }
}