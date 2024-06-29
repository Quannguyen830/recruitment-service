package com.example.recruitment_service.dto.dtoIn;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployerDtoIn {

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(max = 255)
    private String name;

    @NotNull
    private int provinceId;
    private String description;

}
