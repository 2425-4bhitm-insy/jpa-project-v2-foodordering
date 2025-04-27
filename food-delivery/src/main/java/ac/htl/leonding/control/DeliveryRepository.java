package ac.htl.leonding.control;

import ac.htl.leonding.boundary.DeliveryResource;
import ac.htl.leonding.entities.Delivery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;



@ApplicationScoped
public class DeliveryRepository implements PanacheRepository<Delivery> {

    public Delivery findByOrderId(Long orderId) {
        return find("order.id", orderId).firstResult();
    }

    public List<Delivery> findByDeliveryPersonId(Long deliveryPersonId) {
        return list("deliveryperson.id", deliveryPersonId);
    }

    public List<Delivery> findByStatus(String status) {
        return list("status", status);
    }

    public List<Delivery> getAll() {
        return findAll().list();
    }

    public void save(Delivery delivery){
        persist(delivery);
    }

    public void delete(Delivery delivery){
        delete(delivery);
    }

    public void update(Delivery delivery){
        update(delivery);
    }

    public Delivery findDeliveryById(Long id){
        return find("id", id).firstResult();
    }


}
