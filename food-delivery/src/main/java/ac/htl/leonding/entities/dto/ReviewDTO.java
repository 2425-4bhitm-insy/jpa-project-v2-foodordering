package ac.htl.leonding.entities.dto;

import java.time.LocalDateTime;

public record ReviewDTO(
        Long id,
        String rating,
        Long customerId,
        Long restaurantId
) {}