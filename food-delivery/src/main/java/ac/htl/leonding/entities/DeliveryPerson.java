package ac.htl.leonding.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class DeliveryPerson {
    @Id
    private Long id;

    @OneToMany()
    private List<Delivery> deliveries = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
