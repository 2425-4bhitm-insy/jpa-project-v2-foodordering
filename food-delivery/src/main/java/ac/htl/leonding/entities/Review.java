package ac.htl.leonding.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
@Table(name = "REVIEW")
public class Review {

    @Id
    @SequenceGenerator(name = "reviewSequence", sequenceName = "review_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviewSequence")
    private Long id;

    @NotBlank(message = "Rating is required")
    @Column(name = "rating")
    private String rating;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;


    public Review() {
    }

    public Review(String rating) {
        this.rating = rating;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id) &&
                Objects.equals(customer, review.customer) &&
                Objects.equals(restaurant, review.restaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, restaurant);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", rating='" + rating + '\'' +
                ", customerId=" + (customer != null ? customer.getId() : null) +
                ", restaurantId=" + (restaurant != null ? restaurant.getId() : null) +
                '}';
    }
}
