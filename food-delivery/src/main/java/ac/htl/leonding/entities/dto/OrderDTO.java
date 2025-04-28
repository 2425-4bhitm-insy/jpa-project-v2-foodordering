package ac.htl.leonding.entities.dto;

import java.time.LocalDateTime;

public record OrderDTO(
        Long id,
        Long customerId,
        Long restaurantId,
        String status,
        Double totalPrice,
        LocalDateTime orderDate
) {}