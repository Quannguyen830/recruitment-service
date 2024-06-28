package com.example.recruitment_service.dto.DtoIn;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PageDtoIn {

    @NotNull
    @Min(value = 1)
    private int page = 1;

    @NotNull
    @Max(value = 500)
    private int pageSize = 5;

}
