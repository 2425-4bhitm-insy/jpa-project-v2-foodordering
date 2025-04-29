package ac.htl.leonding.entities.dto;

import java.time.LocalDateTime;

public record OrderDto(
        String deliveryAddress,
        LocalDateTime orderDate,
        String status,
        double totalPrice
) {
}
