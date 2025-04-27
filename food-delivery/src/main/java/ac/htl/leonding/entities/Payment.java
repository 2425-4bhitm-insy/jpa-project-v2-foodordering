package ac.htl.leonding.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

@Entity
@Table(name = "PAYMENT")
public class Payment {

    @Id
    @SequenceGenerator(name = "paymentSequence", sequenceName = "payment_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paymentSequence")
    private Long id;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    @Column(name = "amount")
    private Double amount;

    @NotBlank(message = "Payment method is required")
    @Column(name = "paymentmethod")
    private String paymentMethod;

    @NotBlank(message = "Status is required")
    @Column(name = "status")
    private String status = "PENDING";

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;


    public Payment() {
    }

    public Payment(Double amount, String paymentMethod, String status) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) &&
                Objects.equals(order, payment.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", status='" + status + '\'' +
                ", orderId=" + (order != null ? order.getId() : null) +
                '}';
    }
}
