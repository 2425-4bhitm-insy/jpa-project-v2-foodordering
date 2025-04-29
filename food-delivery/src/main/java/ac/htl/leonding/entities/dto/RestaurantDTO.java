package ac.htl.leonding.entities.dto;

import java.util.List;

public record RestaurantDTO(
        String name,
        String address,
        String description,
        String rating,
        Long restaurantOwner
) {}
