package ac.htl.leonding.control;

import ac.htl.leonding.entities.Order;
import ac.htl.leonding.entities.Payment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class PaymentRepository {

    @Inject
    EntityManager em;

    public List<Payment> getAll() {
        return em.createQuery("select p from Payment p", Payment.class).getResultList();
    }

    public void save(Payment payment) {
        em.persist(payment);
    }

    public void delete(Payment payment) {
        em.remove(payment);
    }

    public void update(Payment payment) {

    }
}
