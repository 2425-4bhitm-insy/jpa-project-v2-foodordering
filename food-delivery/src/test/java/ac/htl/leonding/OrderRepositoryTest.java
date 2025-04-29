package ac.htl.leonding;

import ac.htl.leonding.control.CustomerRepository;
import ac.htl.leonding.control.OrderRepository;
import ac.htl.leonding.control.RestaurantRepository;
import ac.htl.leonding.entities.Customer;
import ac.htl.leonding.entities.Order;
import ac.htl.leonding.entities.Restaurant;
import ac.htl.leonding.entities.dto.OrderDTO;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class OrderRepositoryTest {

    @Inject
    OrderRepository orderRepository;

    @Inject
    CustomerRepository customerRepository;

    @Inject
    RestaurantRepository restaurantRepository;

    @Test
    @Transactional
    public void testOrderCRUD() {
        Customer customer = createTestCustomer();
        Restaurant restaurant = createTestRestaurant();

        Order order = new Order();
        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setOrderDate(LocalDateTime.now());
        order.setDeliveryAddress("123 Test St");
        order.setTotalPrice(25.99);
        order.setStatus("Received");

        orderRepository.save(order);
        assertNotNull(order.getId(), "Order ID should not be null after save");

        Order foundOrder = orderRepository.findById(order.getId());
        assertNotNull(foundOrder, "Order should be found by ID");
        assertEquals("Received", foundOrder.getStatus());

        foundOrder.setStatus("Processing");
        Order updatedOrder = orderRepository.update(foundOrder);
        assertEquals("Processing", updatedOrder.getStatus());

        List<Order> allOrders = orderRepository.getAll();
        assertTrue(allOrders.size() > 0, "Order list should not be empty");
        boolean foundTestOrder = allOrders.stream()
                .anyMatch(o -> o.getId().equals(order.getId()));
        assertTrue(foundTestOrder, "Test order should be in the list");

        List<Order> ordersByCustomer = orderRepository.findByCustomerId(customer.getId());
        assertFalse(ordersByCustomer.isEmpty(), "Should find orders by customer ID");
        boolean foundByCustomer = ordersByCustomer.stream()
                .anyMatch(o -> o.getId().equals(order.getId()));
        assertTrue(foundByCustomer, "Test order should be found by customer ID");

        List<Order> ordersByRestaurant = orderRepository.findByRestaurantId(restaurant.getId());
        assertFalse(ordersByRestaurant.isEmpty(), "Should find orders by restaurant ID");
        boolean foundByRestaurant = ordersByRestaurant.stream()
                .anyMatch(o -> o.getId().equals(order.getId()));
        assertTrue(foundByRestaurant, "Test order should be found by restaurant ID");

        List<Order> ordersByStatus = orderRepository.findByStatus("Processing");
        assertFalse(ordersByStatus.isEmpty(), "Should find orders by status");
        boolean foundByStatus = ordersByStatus.stream()
                .anyMatch(o -> o.getId().equals(order.getId()));
        assertTrue(foundByStatus, "Test order should be found by status");

        OrderDTO dto = orderRepository.entityToDTO(updatedOrder);
        assertEquals(order.getId(), dto.id());
        assertEquals("Processing", dto.status());
        assertEquals(customer.getId(), dto.customerId());
        assertEquals(restaurant.getId(), dto.restaurantId());

        Order fromDto = orderRepository.dtoToEntity(dto);
        assertEquals(order.getId(), fromDto.getId());
        assertEquals("Processing", fromDto.getStatus());
        assertEquals(customer.getId(), fromDto.getCustomer().getId());
        assertEquals(restaurant.getId(), fromDto.getRestaurant().getId());

        orderRepository.delete(order);
        Order deletedOrder = orderRepository.findById(order.getId());
        assertNull(deletedOrder, "Order should be deleted");

        cleanupTestData(customer, restaurant);
    }

    @Test
    @Transactional
    public void testDeleteById() {
        Customer customer = createTestCustomer();
        Restaurant restaurant = createTestRestaurant();

        Order order = new Order();
        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setOrderDate(LocalDateTime.now());
        order.setDeliveryAddress("456 Delete St");
        order.setTotalPrice(15.99);
        order.setStatus("Received");

        orderRepository.save(order);
        assertNotNull(order.getId(), "Order ID should not be null after save");

        orderRepository.deleteById(order.getId());

        Order deletedOrder = orderRepository.findById(order.getId());
        assertNull(deletedOrder, "Order should be deleted by ID");

        cleanupTestData(customer, restaurant);
    }

    @Test
    @Transactional
    public void testEntityToDTOList() {
        Customer customer = createTestCustomer();
        Restaurant restaurant = createTestRestaurant();

        Order order1 = new Order();
        order1.setCustomer(customer);
        order1.setRestaurant(restaurant);
        order1.setOrderDate(LocalDateTime.now());
        order1.setDeliveryAddress("123 First St");
        order1.setTotalPrice(20.99);
        order1.setStatus("Delivered");

        Order order2 = new Order();
        order2.setCustomer(customer);
        order2.setRestaurant(restaurant);
        order2.setOrderDate(LocalDateTime.now());
        order2.setDeliveryAddress("456 Second St");
        order2.setTotalPrice(30.99);
        order2.setStatus("Processing");

        orderRepository.save(order1);
        orderRepository.save(order2);

        List<Order> orders = List.of(order1, order2);
        List<OrderDTO> dtos = orderRepository.entityToDTO(orders);

        assertEquals(2, dtos.size());
        assertTrue(dtos.stream().anyMatch(dto -> dto.status().equals("Delivered")));
        assertTrue(dtos.stream().anyMatch(dto -> dto.status().equals("Processing")));

        orderRepository.delete(order1);
        orderRepository.delete(order2);
        cleanupTestData(customer, restaurant);
    }

    @Transactional
    public Customer createTestCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Order");
        customer.setLastName("Tester");
        customer.setEmail("order.test@example.com");
        customer.setPhoneNumber("555-ORDER");

        customerRepository.persist(customer);
        return customer;
    }

    @Transactional
    public Restaurant createTestRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Order Test Restaurant");
        restaurant.setAddress("789 Order Ave");
        restaurant.setDescription("Test Restaurant for Orders");
        restaurant.setRating("4.3");

        restaurantRepository.save(restaurant);
        return restaurant;
    }

    @Transactional
    public void cleanupTestData(Customer customer, Restaurant restaurant) {
        if (customer != null && customer.getId() != null) {
            customerRepository.delete(customer);
        }

        if (restaurant != null && restaurant.getId() != null) {
            restaurantRepository.delete(restaurant);
        }
    }
}