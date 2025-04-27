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

    public List<Delivery> findAll() {
         return em.createNamedQuery("Delivery.findAll", Delivery.class).getResultList();
    }

    public void save (Delivery delivery){
        em.persist(delivery);
    }

    public void delete (Delivery delivery){
        em.remove(em.merge(delivery));
    }

    public Delivery findDeliveryById(Long id){
        return em.createQuery("select d from Delivery d where d.id = :id", Delivery.class).setParameter("id", id).getSingleResult();
    }

   public void update (Delivery delivery){

   }


}
