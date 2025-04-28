package ac.htl.leonding.control;

import ac.htl.leonding.entities.Customer;
import ac.htl.leonding.entities.Order;
import ac.htl.leonding.entities.dto.CustomerDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CustomerRepository {

    @Inject
    EntityManager em;

    public List<Customer> listAll() {
        return em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    public List<Order> findAllOrders(Long customerId) {
        Customer customer = findById(customerId);
        return customer != null ? customer.getOrders() : List.of();
    }

    public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }

    public List<Object[]> findAllCustomersWithOrderInfo() {
        return em.createQuery("SELECT c, COUNT(o), SUM(o.totalPrice) " +
                        "FROM Customer c LEFT JOIN c.orders o " +
                        "GROUP BY c.id")
                .getResultList();
    }

    public CustomerDTO entityToDTO(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getFirstName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getAddress()
        );
    }

    public List<CustomerDTO> entityToDTO(List<Customer> customers) {
        return customers.stream()
                .map(this::entityToDTO)
                .toList();
    }

    public Customer dtoToEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setId(dto.id());
        customer.setFirstName(dto.firstName());
        customer.setLastName(dto.lastName());
        customer.setEmail(dto.email());
        customer.setPhoneNumber(dto.phone());
        customer.setAddress(dto.address());
        return customer;
    }

    @Transactional
    public void persist(Customer customer) {
        em.persist(customer);
    }

    @Transactional
    public Customer update(Customer customer) {
        return em.merge(customer);
    }

    @Transactional
    public void delete(Customer customer) {
        em.remove(em.contains(customer) ? customer : em.merge(customer));
    }

    @Transactional
    public void deleteById(Long id) {
        Customer entity = findById(id);
        if (entity != null) {
            em.remove(entity);
        }
    }
}