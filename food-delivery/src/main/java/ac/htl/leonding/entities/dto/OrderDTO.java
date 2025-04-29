package ac.htl.leonding.entities.dto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
        Long id,
        LocalDateTime orderDate,
        String deliveryAddress,
        Double totalPrice,
        String status,
        Long customerId,
        Long restaurantId
) {}