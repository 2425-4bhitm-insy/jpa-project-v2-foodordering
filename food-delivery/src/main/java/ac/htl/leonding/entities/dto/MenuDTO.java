package ac.htl.leonding.entities.dto;

public record MenuDTO(
        Long id,
        Long restaurantId,
        String name,
        String description
) {}