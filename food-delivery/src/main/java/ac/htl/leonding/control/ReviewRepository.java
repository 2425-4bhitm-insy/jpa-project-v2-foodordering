package ac.htl.leonding.control;

import ac.htl.leonding.entities.Review;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ReviewRepository {

    @Inject
    EntityManager em;

    public List<Review> getAll() {
        return em.createQuery("SELECT r FROM Review r", Review.class).getResultList();
    }

    public Review findById(Long id) {
        return em.find(Review.class, id);
    }

    public List<Review> findByRestaurantId(Long restaurantId) {
        return em.createQuery("SELECT r FROM Review r WHERE r.restaurant.id = :restaurantId", Review.class)
                .setParameter("restaurantId", restaurantId)
                .getResultList();
    }

    public List<Review> findByCustomerId(Long customerId) {
        return em.createQuery("SELECT r FROM Review r WHERE r.customer.id = :customerId", Review.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    public Double getAverageRatingForRestaurant(Long restaurantId) {
        return em.createQuery(
                        "SELECT AVG(CAST(r.rating AS double)) FROM Review r WHERE r.restaurant.id = :restaurantId",
                        Double.class)
                .setParameter("restaurantId", restaurantId)
                .getSingleResult();
    }

    @Transactional
    public void save(Review review) {
        em.persist(review);
    }

    @Transactional
    public Review update(Review review) {
        return em.merge(review);
    }

    @Transactional
    public void delete(Review review) {
        em.remove(em.contains(review) ? review : em.merge(review));
    }

    @Transactional
    public void deleteById(Long id) {
        Review entity = findById(id);
        if (entity != null) {
            em.remove(entity);
        }
    }
}