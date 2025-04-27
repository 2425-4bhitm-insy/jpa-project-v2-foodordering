package ac.htl.leonding.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "DELIVERY")
public class Delivery {

    @Id
    @SequenceGenerator(name = "deliverySequence", sequenceName = "delivery_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deliverySequence")
    private Long id;

    @NotNull(message = "Estimated time is required")
    @Column(name = "estimatedtime")
    private LocalDateTime estimatedTime;

    @NotBlank(message = "Status is required")
    @Column(name = "status")
    private String status = "PENDING";

    @ManyToOne
    @JoinColumn(name = "deliveryperson_id")
    private DeliveryPerson deliveryPerson;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;


    public Delivery() {
    }

    public Delivery(LocalDateTime estimatedTime, String status) {
        this.estimatedTime = estimatedTime;
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(LocalDateTime estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DeliveryPerson getDeliveryPerson() {
        return deliveryPerson;
    }

    public void setDeliveryPerson(DeliveryPerson deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return Objects.equals(id, delivery.id) &&
                Objects.equals(order, delivery.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order);
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", estimatedTime=" + estimatedTime +
                ", status='" + status + '\'' +
                ", deliveryPersonId=" + (deliveryPerson != null ? deliveryPerson.getId() : null) +
                ", orderId=" + (order != null ? order.getId() : null) +
                '}';
    }
}
