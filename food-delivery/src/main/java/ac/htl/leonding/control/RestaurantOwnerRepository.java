package ac.htl.leonding.control;

import ac.htl.leonding.entities.Restaurant;
import ac.htl.leonding.entities.RestaurantOwner;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class RestaurantOwnerRepository implements PanacheRepository<RestaurantOwner> {

    public List<Restaurant> findAllRestaurants(Long ownerId) {
        RestaurantOwner owner = findById(ownerId);
        return owner != null ? owner.getRestaurants() : List.of();
    }
}