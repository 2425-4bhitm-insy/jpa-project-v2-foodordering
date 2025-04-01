package ac.htl.leonding.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class RestaurantOwner extends User {
    @Id
    private Long id;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Restaurant> restaurants = new ArrayList<>();

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
