package com.example.recruitment_service.DtoIn;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageDtoIn {

    @NotEmpty
    @Min(value = 1)
    private int page = 1;

    @NotEmpty
    @Max(value = 500)
    private int pageSize = 5;

}
