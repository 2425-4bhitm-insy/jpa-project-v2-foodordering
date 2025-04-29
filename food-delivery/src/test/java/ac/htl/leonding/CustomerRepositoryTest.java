package ac.htl.leonding;
import ac.htl.leonding.control.CustomerRepository;
import ac.htl.leonding.entities.Customer;
import ac.htl.leonding.entities.dto.CustomerDTO;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class CustomerRepositoryTest {

    @Inject
    CustomerRepository customerRepository;

    @Test
    @Order(1)
    @Transactional
    public void testCustomerCRUD() {
        Customer customer = new Customer();
        customer.setFirstName("Test");
        customer.setLastName("Customer");
        customer.setEmail("test@customer.com");
        customer.setPhoneNumber("555-1234");

        customerRepository.persist(customer);
        assertNotNull(customer.getId(), "Customer ID should not be null after persist");

        Customer foundCustomer = customerRepository.findById(customer.getId());
        assertNotNull(foundCustomer, "Customer should be found by ID");
        assertEquals("Test", foundCustomer.getFirstName());
        assertEquals("Customer", foundCustomer.getLastName());

        foundCustomer.setFirstName("Updated");
        Customer updatedCustomer = customerRepository.update(foundCustomer);
        assertEquals("Updated", updatedCustomer.getFirstName());

        CustomerDTO dto = customerRepository.entityToDTO(updatedCustomer);
        assertEquals("Updated", dto.firstName());
        assertEquals("Customer", dto.lastName());
        assertEquals("test@customer.com", dto.email());

        Customer fromDto = customerRepository.dtoToEntity(dto);
        assertEquals("Updated", fromDto.getFirstName());
        assertEquals("Customer", fromDto.getLastName());

        List<Customer> allCustomers = customerRepository.getAll();
        assertTrue(allCustomers.size() > 0, "Customer list should not be empty");
        boolean foundTestCustomer = allCustomers.stream()
                .anyMatch(c -> c.getId().equals(customer.getId()));
        assertTrue(foundTestCustomer, "Test customer should be in the list");

        customerRepository.delete(customer);
        Customer deletedCustomer = customerRepository.findById(customer.getId());
        assertNull(deletedCustomer, "Customer should be deleted");
    }

    @Test
    @Transactional
    public void testDeleteById() {
        Customer customer = new Customer();
        customer.setFirstName("Delete");
        customer.setLastName("ById");
        customer.setEmail("delete@test.com");
        customer.setPhoneNumber("555-9876");

        customerRepository.persist(customer);
        assertNotNull(customer.getId(), "Customer ID should not be null after persist");

        customerRepository.deleteById(customer.getId());

        Customer deletedCustomer = customerRepository.findById(customer.getId());
        assertNull(deletedCustomer, "Customer should be deleted by ID");
    }

    @Test
    @Transactional
    public void testEntityToDTOList() {

        Customer customer1 = new Customer();
        customer1.setFirstName("First");
        customer1.setLastName("Customer");
        customer1.setEmail("first@test.com");
        customer1.setPhoneNumber("555-1111");

        Customer customer2 = new Customer();
        customer2.setFirstName("Second");
        customer2.setLastName("Customer");
        customer2.setEmail("second@test.com");
        customer2.setPhoneNumber("555-2222");

        customerRepository.persist(customer1);
        customerRepository.persist(customer2);

        List<Customer> customers = List.of(customer1, customer2);
        List<CustomerDTO> dtos = customerRepository.entityToDTO(customers);

        assertEquals(2, dtos.size());
        assertTrue(dtos.stream().anyMatch(dto -> dto.firstName().equals("First")));
        assertTrue(dtos.stream().anyMatch(dto -> dto.firstName().equals("Second")));


        customerRepository.delete(customer1);
        customerRepository.delete(customer2);
    }
}