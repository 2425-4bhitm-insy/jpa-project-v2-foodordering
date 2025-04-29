package ac.htl.leonding.entities.dto;

import java.util.List;

public record MenuDTO(
        Long id,
        String name,
        List<Long> dishIds
) {}