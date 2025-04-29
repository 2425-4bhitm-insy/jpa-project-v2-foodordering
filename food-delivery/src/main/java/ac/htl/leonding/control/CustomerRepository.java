package ac.htl.leonding.control;

import ac.htl.leonding.entities.Customer;
import ac.htl.leonding.entities.Order;
import ac.htl.leonding.entities.Review;
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

    public List<Customer> getAll() {
        List<Customer> customers = em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        return customers;
    }



    public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }

    public CustomerDTO entityToDTO(Customer customer) {
        return new CustomerDTO(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhoneNumber()
        );
    }

    public List<CustomerDTO> entityToDTO(List<Customer> customers) {
        return customers.stream()
                .map(this::entityToDTO)
                .toList();
    }

    public Customer dtoToEntity(CustomerDTO dto) {
        Customer  customer = new Customer();
        customer.setFirstName(dto.firstName());
        customer.setLastName(dto.lastName());
        customer.setEmail(dto.email());
        customer.setPhoneNumber(dto.phoneNumber());
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