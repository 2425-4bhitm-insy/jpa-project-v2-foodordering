package ac.htl.leonding.control;

import ac.htl.leonding.entities.Order;
import ac.htl.leonding.entities.Restaurant;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;



@ApplicationScoped
public class RestaurantRepository implements PanacheRepository<Restaurant> {

    public List<Restaurant> findAllRestaurants() {
        return findAll().list();
    }

    public List<Restaurant> findByName(String name) {
        return list("name LIKE ?1", "%" + name + "%");
    }

    public List<Restaurant> findByRating(String minRating) {
        return list("rating >= ?1", minRating);
    }

    public void save(Restaurant restaurant){
        persist(restaurant);
    }

    public void delete(Restaurant restaurant){
        delete(restaurant);
    }

    public void update(Restaurant restaurant){
        update(restaurant);
    }
}