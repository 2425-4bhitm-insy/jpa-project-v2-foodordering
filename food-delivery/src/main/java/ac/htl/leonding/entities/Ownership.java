package ac.htl.leonding.entities;

import jakarta.persistence.*;

@Entity
public class Ownership {

    @Id
    private Long id;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne
    private RestaurantOwner restaurantOwner;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public RestaurantOwner getRestaurantOwner() {
        return restaurantOwner;
    }

    public void setRestaurantOwner(RestaurantOwner restaurantOwner) {
        this.restaurantOwner = restaurantOwner;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
