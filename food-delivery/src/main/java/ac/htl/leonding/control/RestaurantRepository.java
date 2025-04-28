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

    // Find by name
    public List<Restaurant> findByName(String name) {
        return list("name LIKE ?1", "%" + name + "%");
    }

    // Complex query 2: Find restaurants with rating above threshold and minimum number of reviews
    public List<Restaurant> findTopRatedRestaurants(String minRating, int minReviewCount) {
        return getEntityManager()
                .createQuery(
                        "SELECT r FROM Restaurant r " +
                                "LEFT JOIN r.reviews rv " +
                                "GROUP BY r.id " +
                                "HAVING r.rating >= :minRating AND COUNT(rv) >= :minReviewCount " +
                                "ORDER BY r.rating DESC",
                        Restaurant.class)
                .setParameter("minRating", minRating)
                .setParameter("minReviewCount", minReviewCount)
                .getResultList();
    }

    // Find restaurants by owner
    public List<Restaurant> findByOwner(Long ownerId) {
        return list("restaurantOwner.id", ownerId);
    }
}
