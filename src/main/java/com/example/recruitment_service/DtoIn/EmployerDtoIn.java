package com.example.recruitment_service.DtoIn;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty
    private int provinceId;
    private String description;

}
