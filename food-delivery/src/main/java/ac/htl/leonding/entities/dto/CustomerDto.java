package ac.htl.leonding.entities.dto;

public record CustomerDto(
        String firstName,
        String lastName,
        String email,
        String address,
        String phoneNumber
) {
}
