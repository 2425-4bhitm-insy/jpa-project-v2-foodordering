package ac.htl.leonding.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "ORDERITEM")
public class OrderItem {

    @Id
    @SequenceGenerator(name = "orderItemSequence", sequenceName = "order_item_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderItemSequence")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id")
    private Dish dish;


    public OrderItem() {
    }

    public OrderItem(Order order, Dish dish) {
        this.order = order;
        this.dish = dish;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id) &&
                Objects.equals(order, orderItem.order) &&
                Objects.equals(dish, orderItem.dish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, dish);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", orderId=" + (order != null ? order.getId() : null) +
                ", dishId=" + (dish != null ? dish.getId() : null) +
                ", dishName=" + (dish != null ? dish.getName() : null) +
                '}';
    }
}
