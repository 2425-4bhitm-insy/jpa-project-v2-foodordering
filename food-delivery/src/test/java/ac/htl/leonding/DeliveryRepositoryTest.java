//package ac.htl.leonding;
//
//import ac.htl.leonding.control.DeliveryRepository;
//import ac.htl.leonding.control.OrderRepository;
//import ac.htl.leonding.entities.Delivery;
//import ac.htl.leonding.entities.DeliveryPerson;
//import ac.htl.leonding.entities.Order;
//import io.quarkus.test.junit.QuarkusTest;
//import jakarta.inject.Inject;
//import jakarta.transaction.Transactional;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@QuarkusTest
//public class DeliveryRepositoryTest {
//
//    @Inject
//    DeliveryRepository deliveryRepository;
//
//    @Inject
//    OrderRepository orderRepository;
//
//    @Test
//    @Transactional
//    public void testDeliveryCRUD() {
//        Order order = createTestOrder();
//        DeliveryPerson deliveryPerson = createTestDeliveryPerson();
//
//        Delivery delivery = new Delivery();
//        delivery.setOrder(order);
//        delivery.setStatus("Pending");
//        delivery.setEstimatedTime(LocalDateTime.now().plusHours(1));
//
//        deliveryRepository.persist(delivery);
//        assertNotNull(delivery.getId(), "Delivery ID should not be null after persist");
//
//        Delivery foundDelivery = deliveryRepository.findById(delivery.getId());
//        assertNotNull(foundDelivery, "Delivery should be found by ID");
//        assertEquals("Pending", foundDelivery.getStatus());
//
//        foundDelivery.setStatus("In Transit");
//        Delivery updatedDelivery = deliveryRepository.update(foundDelivery);
//        assertEquals("In Transit", updatedDelivery.getStatus());
//
//        List<Delivery> allDeliveries = deliveryRepository.listAll();
//        assertTrue(allDeliveries.size() > 0, "Delivery list should not be empty");
//        boolean foundTestDelivery = allDeliveries.stream()
//                .anyMatch(d -> d.getId().equals(delivery.getId()));
//        assertTrue(foundTestDelivery, "Test delivery should be in the list");
//
//        Delivery deliveryByOrder = deliveryRepository.findByOrderId(order.getId());
//        assertNotNull(deliveryByOrder, "Delivery should be found by order ID");
//        assertEquals(delivery.getId(), deliveryByOrder.getId());
//
//        List<Delivery> deliveriesByPerson = deliveryRepository.findByDeliveryPersonId(deliveryPerson.getId());
//        assertFalse(deliveriesByPerson.isEmpty(), "Should find deliveries by delivery person ID");
//        boolean foundByPerson = deliveriesByPerson.stream()
//                .anyMatch(d -> d.getId().equals(delivery.getId()));
//        assertTrue(foundByPerson, "Test delivery should be found by delivery person ID");
//
//        List<Delivery> deliveriesByStatus = deliveryRepository.findByStatus("In Transit");
//        assertFalse(deliveriesByStatus.isEmpty(), "Should find deliveries by status");
//        boolean foundByStatus = deliveriesByStatus.stream()
//                .anyMatch(d -> d.getId().equals(delivery.getId()));
//        assertTrue(foundByStatus, "Test delivery should be found by status");
//
//        deliveryRepository.delete(delivery);
//        Delivery deletedDelivery = deliveryRepository.findById(delivery.getId());
//        assertNull(deletedDelivery, "Delivery should be deleted");
//
//        cleanupTestData(order, deliveryPerson);
//    }
//
//    @Test
//    @Transactional
//    public void testDeleteById() {
//        Order order = createTestOrder();
//        DeliveryPerson deliveryPerson = createTestDeliveryPerson();
//
//        // Create a test delivery for deletion
//        Delivery delivery = new Delivery();
//        delivery.setOrder(order);
//        delivery.setStatus("Pending");
//        delivery.setEstimatedTime(LocalDateTime.now().plusHours(1));
//
//        deliveryRepository.persist(delivery);
//        assertNotNull(delivery.getId(), "Delivery ID should not be null after persist");
//
//        boolean result = deliveryRepository.deleteById(delivery.getId());
//        assertTrue(result, "Delete by ID should return true for successful deletion");
//
//        Delivery deletedDelivery = deliveryRepository.findById(delivery.getId());
//        assertNull(deletedDelivery, "Delivery should be deleted by ID");
//
//        cleanupTestData(order, deliveryPerson);
//    }
//
//    @Transactional
//    private Order createTestOrder() {
//        Order order = new Order();
//        order.setStatus("Pending");
//        order.setOrderDate(LocalDateTime.now());
//        order.setDeliveryAddress("123 Test St");
//        orderRepository.save(order);
//        return order;
//    }
//
//    @Transactional
//    private DeliveryPerson createTestDeliveryPerson() {
//        DeliveryPerson person = new DeliveryPerson();
//        person.setFirstName("Test");
//        person.setLastName("Driver");
//        person.setEmail("test.driver@example.com");
//        person.setPhoneNumber("555-DRIVER");
//        // Persist using EntityManager directly in a real implementation
//        return person;
//    }
//
//    @Transactional
//    private void cleanupTestData(Order order, DeliveryPerson person) {
//        if (order != null && order.getId() != null) {
//            orderRepository.delete(order);
//        }
//
//    }
//}