package ac.htl.leonding.control;

import ac.htl.leonding.entities.Order;
import ac.htl.leonding.entities.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class UserRepository {

    @Inject
    EntityManager em;

    public List<User> getAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    public void save(User user){
        em.persist(user);
    }

    public void delete(User user){
        em.remove(user);
    }

    public void update(User user){

    }
}
