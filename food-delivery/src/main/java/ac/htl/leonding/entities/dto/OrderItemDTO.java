package ac.htl.leonding.entities.dto;

public record OrderItemDTO(
        Long dishId,
        String dishName,
        Double price
) {}