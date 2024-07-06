package com.example.recruitment_service.dto.dtoIn.entity;

import com.example.recruitment_service.common.annotation.ValidDate;
import com.example.recruitment_service.model.Seeker;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeekerDtoIn {

    @NotEmpty
    private String name;

    @NotEmpty
    @ValidDate
    private String birthday;

    private String address;

    @NotNull
    private Integer provinceId;

    public static Seeker from(SeekerDtoIn seekerDtoIn) {
        return Seeker.builder().name(seekerDtoIn.name).birthday(seekerDtoIn.birthday)
                .address(seekerDtoIn.address).province(seekerDtoIn.provinceId).created_at(LocalDate.now())
                .updated_at(LocalDate.now()).build();
    }

}
