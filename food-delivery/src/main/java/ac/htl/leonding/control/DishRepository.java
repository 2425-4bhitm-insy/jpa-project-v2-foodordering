package ac.htl.leonding.control;

import ac.htl.leonding.entities.Dish;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class DishRepository implements PanacheRepository<Dish> {

    public List<Dish> findByCategory(String category) {
        return list("category", category);
    }

    public List<Dish> findAvailableByMenuId(Long menuId) {
        return list("menu.id = ?1 AND isavailable = true", menuId);
    }
}
