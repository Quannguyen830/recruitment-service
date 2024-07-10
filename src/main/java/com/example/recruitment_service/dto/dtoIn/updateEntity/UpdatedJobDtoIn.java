package com.example.recruitment_service.dto.dtoIn.updateEntity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatedJobDtoIn {

    @NotNull
    private BigInteger id;
    @NotEmpty
    private String title;
    @NotNull
    private Integer quantity;
    @NotEmpty
    private String description;
    @NotEmpty
    private String fieldIds;
    @NotEmpty
    private String provinceIds;
    @NotNull
    private Integer salary;
    @NotEmpty
    private LocalDate expiredAt;

}
