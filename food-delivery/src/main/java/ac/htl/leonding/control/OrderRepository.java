package ac.htl.leonding.control;

import ac.htl.leonding.entities.Order;
import ac.htl.leonding.entities.dto.OrderDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class OrderRepository {

    @Inject
    EntityManager em;

    public List<Order> getAll() {
        return em.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }

    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findByCustomerId(Long customerId) {
        return em.createQuery("SELECT o FROM Order o WHERE o.customer.id = :customerId", Order.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    public List<Order> findByRestaurantId(Long restaurantId) {
        return em.createQuery("SELECT o FROM Order o WHERE o.restaurant.id = :restaurantId", Order.class)
                .setParameter("restaurantId", restaurantId)
                .getResultList();
    }

    public List<Order> findByStatus(String status) {
        return em.createQuery("SELECT o FROM Order o WHERE o.status = :status", Order.class)
                .setParameter("status", status)
                .getResultList();
    }

    @Transactional
    public void save(Order order) {
        em.persist(order);
    }

    @Transactional
    public Order update(Order order) {
        return em.merge(order);
    }

    @Transactional
    public void delete(Order order) {
        em.remove(em.contains(order) ? order : em.merge(order));
    }

    @Transactional
    public void deleteById(Long id) {
        Order entity = findById(id);
        if (entity != null) {
            em.remove(entity);
        }
    }

    public OrderDTO entityToDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getCustomer() != null ? order.getCustomer().getId() : null,
                order.getRestaurant() != null ? order.getRestaurant().getId() : null,
                order.getStatus(),
                order.getTotalPrice(),
                order.getOrderDate()

        );
    }

    public List<OrderDTO> entityToDTO(List<Order> orders) {
        return orders.stream()
                .map(this::entityToDTO)
                .toList();
    }

    public Order dtoToEntity(OrderDTO dto) {
        Order order = new Order();
        order.setId(dto.id());
        // Customer und Restaurant müssen separat gesetzt werden
        order.setStatus(dto.status());
        order.setTotalPrice(dto.totalPrice());
        order.setOrderDate(dto.orderDate());

        return order;
    }
}