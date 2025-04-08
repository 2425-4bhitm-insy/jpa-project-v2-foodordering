package ac.htl.leonding.control;

import ac.htl.leonding.entities.Delivery;
import ac.htl.leonding.entities.Order;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class OrderRepository {

    @Inject
    private EntityManager em;


    public List<Order> getAll() {
        return em.createQuery("select o from Order o", Order.class).getResultList();
    }

    public void save(Order order){
        em.persist(order);
    }

    public void delete(Order order){
        em.remove(em.merge(order));
    }

    public void update(Order order){

    }
}
