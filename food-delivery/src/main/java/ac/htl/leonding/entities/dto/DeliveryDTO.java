package ac.htl.leonding.entities.dto;

import java.time.LocalDateTime;

public record DeliveryDTO(
        Long id,
        Long orderId,
        Long deliveryPersonId,
        String status,
        LocalDateTime estimatedDeliveryTime,
        LocalDateTime actualDeliveryTime
) {}