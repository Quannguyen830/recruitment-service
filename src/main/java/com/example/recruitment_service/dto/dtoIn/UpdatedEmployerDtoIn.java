package com.example.recruitment_service.dto.dtoIn;

import com.example.recruitment_service.model.Employer;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
