package ac.htl.leonding.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Menu {
    @Id
    private Long id;
    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
