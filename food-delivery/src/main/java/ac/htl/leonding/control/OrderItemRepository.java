package ac.htl.leonding.control;

import ac.htl.leonding.entities.OrderItem;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class OrderItemRepository {

    @Inject
    EntityManager em;

    public List<OrderItem> listAll() {
        return em.createQuery("SELECT oi FROM OrderItem oi", OrderItem.class).getResultList();
    }

    public OrderItem findById(Long id) {
        return em.find(OrderItem.class, id);
    }

    public List<OrderItem> findByOrderId(Long orderId) {
        return em.createQuery("SELECT oi FROM OrderItem oi WHERE oi.order.id = :orderId", OrderItem.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    public List<OrderItem> findByDishId(Long dishId) {
        return em.createQuery("SELECT oi FROM OrderItem oi WHERE oi.dish.id = :dishId", OrderItem.class)
                .setParameter("dishId", dishId)
                .getResultList();
    }

    @Transactional
    public void persist(OrderItem orderItem) {
        em.persist(orderItem);
    }

    @Transactional
    public OrderItem update(OrderItem orderItem) {
        return em.merge(orderItem);
    }

    @Transactional
    public void delete(OrderItem orderItem) {
        em.remove(em.contains(orderItem) ? orderItem : em.merge(orderItem));
    }

    @Transactional
    public boolean deleteById(Long id) {
        OrderItem entity = findById(id);
        if (entity != null) {
            em.remove(entity);
            return true;
        }
        return false;
    }

    // Query for most popular dishes per restaurant
    public List<Object[]> findMostPopularDishesPerRestaurant() {
        return em.createQuery(
                        "SELECT r, d, COUNT(oi) as orderCount " +
                                "FROM Restaurant r JOIN r.orders o JOIN o.orderItems oi JOIN oi.dish d " +
                                "GROUP BY r.id, d.id " +
                                "ORDER BY r.id, orderCount DESC")
                .getResultList();
    }
}