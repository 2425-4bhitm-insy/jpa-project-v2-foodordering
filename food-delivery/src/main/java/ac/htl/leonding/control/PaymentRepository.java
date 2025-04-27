package ac.htl.leonding.control;

import ac.htl.leonding.entities.Order;
import ac.htl.leonding.entities.Payment;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;



@ApplicationScoped
public class PaymentRepository implements PanacheRepository<Payment> {

    public List<Payment> getAll() {
        return findAll().list();
    }

    public Payment findByOrderId(Long orderId) {
        return find("order.id", orderId).firstResult();
    }

    public List<Payment> findByStatus(String status) {
        return list("status", status);
    }

    public void save(Payment payment) {
        persist(payment);
    }

    public void delete(Payment payment) {
        delete(payment);
    }

    public void update(Payment payment) {
        update(payment);
    }
}
