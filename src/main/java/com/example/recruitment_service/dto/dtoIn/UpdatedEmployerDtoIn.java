package com.example.recruitment_service.dto.dtoIn;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
    @Size(max = 500)
    private String username;

    @NotEmpty
    private int provinceId;
    private String description;

}
