package com.example.recruitment_service.dto.dtoIn;

import com.example.recruitment_service.model.Employer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

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

    public static Employer from(EmployerDtoIn employerDtoIn) {
        return Employer.builder().email(employerDtoIn.email).name(employerDtoIn.name)
                .province(employerDtoIn.provinceId).description(employerDtoIn.description)
                .createdAt(LocalDate.now()).updatedAt(LocalDate.now()).build();
    }

}
