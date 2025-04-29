package ac.htl.leonding.entities;

import ac.htl.leonding.entities.dto.OrderDTO;
import ac.htl.leonding.entities.dto.OrderItemDTO;
import jakarta.persistence.*;

import java.util.*;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @SequenceGenerator(name = "orderSequence", sequenceName = "order_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderSequence")
    private Long id;

    @NotNull(message = "Order date is required")
    @Column(name = "orderdate")
    private LocalDateTime orderDate;

    @NotBlank(message = "Delivery address is required")
    @Column(name = "deliveryaddress")
    private String deliveryAddress;

    @NotNull(message = "Total price is required")
    @Positive(message = "Total price must be positive")
    @Column(name = "totalprice")
    private Double totalPrice;

    @NotBlank(message = "Status is required")
    @Column(name = "status")
    private String status = "PENDING";

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Restaurant restaurant;


    @OneToMany(cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Dish> dishes = new ArrayList<>();



    public Order() {
    }

    public Order(LocalDateTime orderDate, String deliveryAddress, Double totalPrice, String status) {
        this.orderDate = orderDate;
        this.deliveryAddress = deliveryAddress;
        this.totalPrice = totalPrice;
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(orderDate, order.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDate);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                '}';
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Dish> getDish() {
        return dishes;
    }

    public void setDish(List<Dish> dish) {
        this.dishes = dish;
    }

    public List<OrderItemDTO> getOrderItems(Long id) {
        return dishes.stream()
                .filter(dish -> dish.getId().equals(id))
                .map(dish -> new OrderItemDTO(dish.getId(), dish.getName(), dish.getPrice()))
                .toList();
    }
}
