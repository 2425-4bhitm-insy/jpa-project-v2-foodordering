package ac.htl.leonding.entities.dto;

public record RestaurantDTO(
        Long id,
        String name,
        String address,
        String description,
        String rating,
        Boolean isOpen
) {}
