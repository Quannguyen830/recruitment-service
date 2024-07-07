package com.example.recruitment_service.dto.response;

import com.example.recruitment_service.model.Employer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployerDtoOut implements Serializable {
    private Long id;
    private String email;
    private String name;
    private Integer provinceId;
    private String provinceName;
    private String description;

    public static EmployerDtoOut from(Employer employer) {
        return EmployerDtoOut.builder().id(employer.getId()).email(employer.getEmail()).name(employer.getName())
                .provinceId(employer.getProvince()).provinceName(employer.getProvince_name()).description(employer.getDescription()).build();
    }
}
