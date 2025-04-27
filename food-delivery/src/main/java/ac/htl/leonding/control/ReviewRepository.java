package ac.htl.leonding.control;

import ac.htl.leonding.entities.Order;
import ac.htl.leonding.entities.Review;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;



@ApplicationScoped
public class ReviewRepository implements PanacheRepository<Review> {

    public List<Review> getAll() {
        return findAll().list();
    }

    public List<Review> findByRestaurantId(Long restaurantId) {
        return list("restaurant.id", restaurantId);
    }

    public List<Review> findByCustomerId(Long customerId) {
        return list("customer.id", customerId);
    }

    public Double getAverageRatingForRestaurant(Long restaurantId) {
        return find("restaurant.id", restaurantId)
                .project(Double.class)
                .firstResult();
    }

    public void save(Review review) {
        persist(review);
    }

    public void delete(Review review) {
        delete(review);
    }

    public void update(Review review) {
        update(review);
    }
}
