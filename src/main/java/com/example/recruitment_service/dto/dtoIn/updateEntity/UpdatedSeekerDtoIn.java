package com.example.recruitment_service.dto.dtoIn.updateEntity;

import com.example.recruitment_service.common.annotation.ValidDate;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedSeekerDtoIn {

    @NotNull
    private BigInteger id;

    @NotEmpty
    private String name;

    @NotEmpty
    @ValidDate
    private String birthday;

    private String address;

    @NotNull
    private Integer provinceId;

}
