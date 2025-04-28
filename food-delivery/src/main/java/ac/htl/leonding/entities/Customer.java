package ac.htl.leonding.entities;

import ac.htl.leonding.entities.dto.CustomerDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
@DiscriminatorValue("CUSTOMER")
public class Customer extends User {

    @OneToMany(cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();


    public Customer() {
        super();
    }

    public Customer(String firstName, String lastName, String email, String phoneNumber, String address) {
        super(firstName, lastName, email, phoneNumber, address);
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


    public void addOrder(Order order) {
        orders.add(order);
        order.setCustomer(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setCustomer(null);
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.setCustomer(this);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
        review.setCustomer(null);
    }



    @Override
    public String toString() {
        return "Customer{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", orders=" + orders.size() +
                ", reviews=" + reviews.size() +
                '}';
    }
}

