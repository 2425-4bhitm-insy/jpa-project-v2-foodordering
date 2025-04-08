package ac.htl.leonding.control;

import ac.htl.leonding.entities.Order;
import ac.htl.leonding.entities.Review;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class ReviewRepository {

    @Inject
    EntityManager em;

    public List<Review> getAll() {
        return em.createQuery("select r from Review r", Review.class).getResultList();
    }

    public void save(Review review){
        em.persist(review);
    }

    public void delete(Review review){
        em.remove(em.merge(review));
    }

    public void update(Review review){

    }
}
