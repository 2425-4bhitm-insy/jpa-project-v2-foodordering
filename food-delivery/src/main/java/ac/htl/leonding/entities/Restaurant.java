package ac.htl.leonding.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "RESTAURANT")
public class Restaurant {

    @Id
    @SequenceGenerator(name = "restaurantSequence", sequenceName = "restaurant_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurantSequence")
    private Long id;

    @NotBlank(message = "Restaurant name is required")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Restaurant address is required")
    @Column(name = "address")
    private String address;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "rating")
    private String rating;

    @ManyToOne
    @JoinColumn(name = "restaurantowner_id")
    private RestaurantOwner restaurantOwner;

    @OneToOne(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Menu menu;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Ownership> ownerships = new ArrayList<>();


    public Restaurant() {
    }

    public Restaurant(String name, String address, String description) {
        this.name = name;
        this.address = address;
        this.description = description;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public RestaurantOwner getRestaurantOwner() {
        return restaurantOwner;
    }

    public void setRestaurantOwner(RestaurantOwner restaurantOwner) {
        this.restaurantOwner = restaurantOwner;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
        if (menu != null) {
            menu.setRestaurant(this);
        }
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Ownership> getOwnerships() {
        return ownerships;
    }

    public void setOwnerships(List<Ownership> ownerships) {
        this.ownerships = ownerships;
    }


    public void addOrder(Order order) {
        orders.add(order);
        order.setRestaurant(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setRestaurant(null);
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.setRestaurant(this);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
        review.setRestaurant(null);
    }

    public void addOwnership(Ownership ownership) {
        ownerships.add(ownership);
        ownership.setRestaurant(this);
    }

    public void removeOwnership(Ownership ownership) {
        ownerships.remove(ownership);
        ownership.setRestaurant(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
