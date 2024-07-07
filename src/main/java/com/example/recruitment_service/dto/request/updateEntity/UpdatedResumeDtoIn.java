package com.example.recruitment_service.dto.request.updateEntity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedResumeDtoIn {

    @NotNull
    private BigInteger id;
    @NotEmpty
    private String careerObj;
    @NotEmpty
    private String title;
    @NotNull
    private Integer salary;
    @NotEmpty
    private String fieldIds;
    @NotEmpty
    private String provinceIds;

}
