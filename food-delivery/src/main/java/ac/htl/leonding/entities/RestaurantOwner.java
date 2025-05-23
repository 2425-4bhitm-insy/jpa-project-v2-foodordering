package ac.htl.leonding.entities;

import jakarta.persistence.*;
import net.bytebuddy.description.modifier.Ownership;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RESTAURANTOWNER")
@DiscriminatorValue("RESTAURANTOWNER")
public class RestaurantOwner extends User {

    @OneToMany( cascade = CascadeType.ALL)
    private List<Restaurant> restaurants = new ArrayList<>();


    public RestaurantOwner() {
        super();
    }

    public RestaurantOwner(String firstName, String lastName, String email, String phoneNumber, String address) {
        super(firstName, lastName, email, phoneNumber, address);
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }


    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
        restaurant.setRestaurantOwner(this);
    }

    public void removeRestaurant(Restaurant restaurant) {
        restaurants.remove(restaurant);
        restaurant.setRestaurantOwner(null);
    }


    @Override
    public String toString() {
        return "RestaurantOwner{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", restaurants=" + restaurants.size() +
                '}';
    }
}
