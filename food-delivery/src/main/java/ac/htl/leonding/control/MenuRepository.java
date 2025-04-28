package ac.htl.leonding.control;

import ac.htl.leonding.entities.Menu;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class MenuRepository {

    @Inject
    EntityManager em;

    public List<Menu> listAll() {
        return em.createQuery("SELECT m FROM Menu m", Menu.class).getResultList();
    }

    public Menu findById(Long id) {
        return em.find(Menu.class, id);
    }

    public Menu findMenuByRestaurantId(Long restaurantId) {
        return em.createQuery("SELECT m FROM Menu m WHERE m.restaurant.id = :restaurantId", Menu.class)
                .setParameter("restaurantId", restaurantId)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public void persist(Menu menu) {
        em.persist(menu);
    }

    @Transactional
    public Menu update(Menu menu) {
        return em.merge(menu);
    }

    @Transactional
    public void delete(Menu menu) {
        em.remove(em.contains(menu) ? menu : em.merge(menu));
    }

    @Transactional
    public void deleteById(Long id) {
        Menu entity = findById(id);
        if (entity != null) {
            em.remove(entity);
        }
    }
}