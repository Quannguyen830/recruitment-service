package com.example.recruitment_service.dto.dtoIn;

import com.example.recruitment_service.model.Job;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty
    private BigInteger id;
    @NotEmpty
    private String title;
    @NotEmpty
    private Integer quantity;
    @NotEmpty
    private String description;
    @NotEmpty
    private String fieldIds;
    @NotEmpty
    private String provinceIds;
    @NotEmpty
    private Integer salary;
    @NotEmpty
    private LocalDate expiredAt;

    public Job from() {
        return Job.builder().id(this.id).title(this.title).quantity(this.quantity)
                .description(this.description).fields(fieldIds).provinces(provinceIds)
                .salary(salary).expired_at(expiredAt).build();
    }

}
