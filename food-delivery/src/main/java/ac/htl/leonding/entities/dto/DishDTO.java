package ac.htl.leonding.entities.dto;

public record DishDTO(
        Long id,
        String name,
        Double price,
        String category,
        Boolean isAvailable,
        Long menuId
) {}