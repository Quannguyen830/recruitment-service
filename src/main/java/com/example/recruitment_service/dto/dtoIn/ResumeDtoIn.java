package com.example.recruitment_service.dto.dtoIn;

import com.example.recruitment_service.model.Resume;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeDtoIn {

    @NotNull
    private BigInteger seekerId;
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

    public static Resume from(ResumeDtoIn resumeDtoIn) {
        return Resume.builder().seeker_id(resumeDtoIn.seekerId).career_obj(resumeDtoIn.careerObj)
                .title(resumeDtoIn.title).salary(resumeDtoIn.salary).fields(resumeDtoIn.fieldIds)
                .provinces(resumeDtoIn.provinceIds).created_at(LocalDate.now()).updated_at(LocalDate.now()).build();
    }

}
