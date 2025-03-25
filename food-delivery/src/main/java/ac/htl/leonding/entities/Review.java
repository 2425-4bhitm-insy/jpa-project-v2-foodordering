package ac.htl.leonding.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Review {
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
