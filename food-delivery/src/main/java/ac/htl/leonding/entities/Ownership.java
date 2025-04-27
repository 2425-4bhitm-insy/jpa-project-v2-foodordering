package ac.htl.leonding.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "OWNERSHIP")
public class Ownership {

    @Id
    @SequenceGenerator(name = "ownershipSequence", sequenceName = "ownership_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ownershipSequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "restaurantowner_id")
    private RestaurantOwner owner;


    public Ownership() {
    }

    public Ownership(Restaurant restaurant, RestaurantOwner owner) {
        this.restaurant = restaurant;
        this.owner = owner;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public RestaurantOwner getOwner() {
        return owner;
    }

    public void setOwner(RestaurantOwner owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ownership ownership = (Ownership) o;
        return Objects.equals(id, ownership.id) &&
                Objects.equals(restaurant, ownership.restaurant) &&
                Objects.equals(owner, ownership.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, restaurant, owner);
    }

    @Override
    public String toString() {
        return "Ownership{" +
                "id=" + id +
                ", restaurantId=" + (restaurant != null ? restaurant.getId() : null) +
                ", ownerId=" + (owner != null ? owner.getId() : null) +
                '}';
    }
}
