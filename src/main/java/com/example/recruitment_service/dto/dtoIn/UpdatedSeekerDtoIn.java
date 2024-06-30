package com.example.recruitment_service.dto.dtoIn;

import com.example.recruitment_service.common.annotation.ValidBirthday;
import com.example.recruitment_service.model.Seeker;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedSeekerDtoIn {

    @NotNull
    private BigInteger id;

    @NotEmpty
    private String name;

    @NotEmpty
    @ValidBirthday
    private String birthday;

    private String address;

    @NotNull
    private Integer provinceId;

}
