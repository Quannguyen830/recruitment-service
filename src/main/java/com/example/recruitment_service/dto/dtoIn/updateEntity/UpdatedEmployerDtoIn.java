package com.example.recruitment_service.dto.dtoIn.updateEntity;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedEmployerDtoIn {

    @NotEmpty
    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private int provinceId;
    private String description;

}
