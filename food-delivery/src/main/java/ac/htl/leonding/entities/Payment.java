package ac.htl.leonding.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Payment {
    @Id
    private Long id;
    private String paymentMethod;
    private String status;
    private double amount;

    public Payment(Long id, String paymentMethod, String status, double amount) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.amount = amount;
    }

    public Payment() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
