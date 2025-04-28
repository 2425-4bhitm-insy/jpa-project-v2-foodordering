package ac.htl.leonding.control;



import ac.htl.leonding.entities.Customer;
import ac.htl.leonding.entities.Order;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {

    public List<Customer> getAll() {
        return findAll().list();
    }

    public Customer findByID(Long )

    public Customer findByEmail(String email) {
        return find("email", email).firstResult();
    }


    public List<Customer> findCustomersWithOrdersFromRestaurant(Long restaurantId) {
        return getEntityManager()
                .createQuery(
                        "SELECT DISTINCT c FROM Customer c " +
                                "JOIN FETCH c.orders o " +
                                "WHERE o.restaurant.id = :restaurantId",
                        Customer.class)
                .setParameter("restaurantId", restaurantId)
                .getResultList();
    }


    public List<Customer> findTopCustomersByOrderCount(int limit) {
        return getEntityManager()
                .createQuery(
                        "SELECT c FROM Customer c " +
                                "LEFT JOIN c.orders o " +
                                "GROUP BY c.id " +
                                "ORDER BY COUNT(o) DESC",
                        Customer.class)
                .setMaxResults(limit)
                .getResultList();
    }
}