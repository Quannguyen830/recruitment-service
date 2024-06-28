package com.example.recruitment_service.dto.DtoIn;

import com.example.recruitment_service.model.Job;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDtoIn {

    @NotNull
    private Long employerId;
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
    @NotNull
    private LocalDate expiredAt;

    public Job from() {
        return Job.builder().employerId(this.employerId).title(this.title).quantity(this.quantity)
                .description(this.description).fields(fieldIds).provinces(provinceIds)
                .salary(salary).expired_at(expiredAt).build();
    }

}
