package ac.htl.leonding.entities.dto;

import java.time.LocalDateTime;

public record DeliveryDTO(
        Long id,
        LocalDateTime estimatedTime,
        String status,
        Long orderId
) {}