package ac.htl.leonding.control;

import ac.htl.leonding.entities.Delivery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

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

    public List<Delivery> listAll() {
        return listAll();
    }

    public Delivery findById(Long id){
        return find("id", id).firstResult();
    }

    public boolean deleteById(Long id) {
        try {
            deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
