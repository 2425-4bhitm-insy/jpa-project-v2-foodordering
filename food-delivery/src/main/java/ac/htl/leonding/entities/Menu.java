package ac.htl.leonding.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "MENU")
public class Menu {

    @Id
    @SequenceGenerator(name = "menuSequence", sequenceName = "menu_id_seq", allocationSize = 1, initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menuSequence")
    private Long id;

    @NotBlank(message = "Menu name is required")
    @Column(name = "name")
    private String name;


    @OneToMany(cascade = CascadeType.ALL)
    private List<Dish> dishes = new ArrayList<>();


    public Menu() {
    }

    public Menu(String name) {
        this.name = name;
    }


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

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }


    public void addDish(Dish dish) {
        dishes.add(dish);
        dish.setMenu(this);
    }

    public void removeDish(Dish dish) {
        dishes.remove(dish);
        dish.setMenu(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(id, menu.id) &&
                Objects.equals(name, menu.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dishes=" + dishes.size() +
                '}';
    }
}
