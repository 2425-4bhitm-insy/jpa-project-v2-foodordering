package ac.htl.leonding.control;

import ac.htl.leonding.entities.Menu;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MenuRepository implements PanacheRepository<Menu> {

    public Menu findMenuByRestaurantId(Long restaurantId) {
        return find("restaurant.id", restaurantId).firstResult();
    }
}