package ac.htl.leonding.entities.dto;

import java.util.List;

public record CustomerDTO(
        String firstName,
        String lastName,
        String email,
        String phoneNumber
) {

}
