package ac.htl.leonding.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "DISH")
public class Dish {

    @Id
    @SequenceGenerator(name = "dishSequence", sequenceName = "dish_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dishSequence")
    private Long id;

    @NotBlank(message = "Dish name is required")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    @Column(name = "price")
    private Double price;

    @Column(name = "category")
    private String category;

    @Column(name = "isavailable")
    private Boolean isAvailable = true;

    @ManyToOne(cascade = CascadeType.ALL)
    private Menu menu;


    // Constructors
    public Dish() {
    }

    public Dish(String name, Double price, String category, Boolean isAvailable) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.isAvailable = isAvailable;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Objects.equals(id, dish.id) &&
                Objects.equals(name, dish.name) &&
                Objects.equals(price, dish.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}

