package ac.htl.leonding.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class RestaurantOwner extends User {
    @Id
    private Long id;

    @OneToMany(mappedBy = "id")
    private List<Ownership> ownerships;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
