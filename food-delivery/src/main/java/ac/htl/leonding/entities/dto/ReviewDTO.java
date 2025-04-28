package ac.htl.leonding.entities.dto;

import java.time.LocalDateTime;

public record ReviewDTO(
        Long id,
        Long restaurantId,
        Long customerId,
        Integer rating,
        String comment,
        LocalDateTime reviewDate
) {}