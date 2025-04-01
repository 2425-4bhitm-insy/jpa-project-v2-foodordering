package ac.htl.leonding.control;

import ac.htl.leonding.entities.Order;
import ac.htl.leonding.entities.Restaurant;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class RestaurantRepository {

    @Inject
    private EntityManager em;


    public List<Restaurant> getAll() {
        return em.createQuery("select r from Restaurant r", Restaurant.class).getResultList();
    }

    public void save(Restaurant restaurant){
        em.persist(restaurant);
    }


}
