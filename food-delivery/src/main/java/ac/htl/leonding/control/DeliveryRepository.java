package ac.htl.leonding.control;

import ac.htl.leonding.entities.Delivery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class DeliveryRepository {

    @Inject
    EntityManager em;

    public List<Delivery> listAll() {
        return em.createQuery("SELECT d FROM Delivery d", Delivery.class).getResultList();
    }

    public Delivery findById(Long id) {
        return em.find(Delivery.class, id);
    }

    public Delivery findByOrderId(Long orderId) {
        return em.createQuery("SELECT d FROM Delivery d WHERE d.order.id = :orderId", Delivery.class)
                .setParameter("orderId", orderId)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    public List<Delivery> findByDeliveryPersonId(Long deliveryPersonId) {
        return em.createQuery("SELECT d FROM Delivery d WHERE d.deliveryperson.id = :deliveryPersonId", Delivery.class)
                .setParameter("deliveryPersonId", deliveryPersonId)
                .getResultList();
    }

    public List<Delivery> findByStatus(String status) {
        return em.createQuery("SELECT d FROM Delivery d WHERE d.status = :status", Delivery.class)
                .setParameter("status", status)
                .getResultList();
    }

    @Transactional
    public void persist(Delivery delivery) {
        em.persist(delivery);
    }

    @Transactional
    public Delivery update(Delivery delivery) {
        return em.merge(delivery);
    }

    @Transactional
    public boolean deleteById(Long id) {
        try {
            Delivery entity = findById(id);
            if (entity != null) {
                em.remove(entity);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public void delete(Delivery delivery) {
        em.remove(em.contains(delivery) ? delivery : em.merge(delivery));
    }
}