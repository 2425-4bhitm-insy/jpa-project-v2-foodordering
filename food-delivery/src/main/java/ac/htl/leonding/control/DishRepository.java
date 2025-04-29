package ac.htl.leonding.control;

import ac.htl.leonding.entities.Dish;
import ac.htl.leonding.entities.dto.DishDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class DishRepository {

    @Inject
    EntityManager em;

    public List<Dish> listAll() {
        return em.createQuery("SELECT d FROM Dish d", Dish.class).getResultList();
    }

    public Dish findById(Long id) {
        return em.find(Dish.class, id);
    }

    public List<Dish> findByCategory(String category) {
        return em.createQuery("SELECT d FROM Dish d WHERE d.category = :category", Dish.class)
                .setParameter("category", category)
                .getResultList();
    }

    public List<Dish> findAvailableByMenuId(Long menuId) {
        return em.createQuery("SELECT d FROM Dish d WHERE d.menu.id = :menuId", Dish.class)
                .setParameter("menuId", menuId)
                .getResultList();
    }

    @Transactional
    public void persist(Dish dish) {
        em.persist(dish);
    }

    @Transactional
    public Dish update(Dish dish) {
        return em.merge(dish);
    }

    @Transactional
    public void delete(Dish dish) {
        em.remove(em.contains(dish) ? dish : em.merge(dish));
    }

    @Transactional
    public void deleteById(Long id) {
        Dish entity = findById(id);
        if (entity != null) {
            em.remove(entity);
        }
    }

    public List<DishDTO> entityToDTO(List<Dish> dishes) {
        return dishes.stream()
                .map(dish -> new DishDTO(dish.getId(), dish.getName(), dish.getPrice(), dish.getCategory(), true, dish.getMenu().getId()))
                .toList();
    }
}