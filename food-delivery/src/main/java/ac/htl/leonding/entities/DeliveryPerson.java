package ac.htl.leonding.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DELIVERYPERSON")
@DiscriminatorValue("DELIVERYPERSON")
public class DeliveryPerson extends User {

    @OneToMany( cascade = CascadeType.ALL)
    private List<Delivery> deliveries = new ArrayList<>();


    public DeliveryPerson() {
        super();
    }

    public DeliveryPerson(String firstName, String lastName, String email, String phoneNumber, String address) {
        super(firstName, lastName, email, phoneNumber, address);
    }


    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }




    @Override
    public String toString() {
        return "DeliveryPerson{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", deliveries=" + deliveries.size() +
                '}';
    }
}
