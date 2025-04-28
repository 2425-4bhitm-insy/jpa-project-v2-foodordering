package ac.htl.leonding.entities.dto;

public record CustomerDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String address
) {}
