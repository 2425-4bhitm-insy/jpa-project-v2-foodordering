package ac.htl.leonding.control;

import ac.htl.leonding.boundary.DeliveryResource;
import ac.htl.leonding.entities.Delivery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class DeliveryRepository {

    @Inject
    EntityManager em;



    public List<Delivery> getAll() {
        return em.createQuery("select d from Delivery d", Delivery.class).getResultList();
    }

    public void save (Delivery delivery){
        em.persist(delivery);
    }




}
