package ac.htl.leonding.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.util.Date;

@Entity
public class Delivery {
    @Id
    private Long id;
    private String status;
    private Date estimatedTime;

    @ManyToOne
    private DeliveryPerson deliveryPerson;

    @OneToOne
    private Order order;

    public Delivery() {
    }

    public Delivery(Long id, String status, Date estimatedTime) {
        this.id = id;
        this.status = status;
        this.estimatedTime = estimatedTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
