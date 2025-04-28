package ac.htl.leonding.entities.dto;

public record DishDTO(
        Long id,
        Long menuId,
        String name,
        String description,
        String category,
        Double price,
        Boolean isAvailable
) {}