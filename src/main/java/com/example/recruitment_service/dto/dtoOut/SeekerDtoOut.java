package com.example.recruitment_service.dto.dtoOut;

import com.example.recruitment_service.model.Seeker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeekerDtoOut {

    private BigInteger id;
    private String name;
    private String birthday;
    private String address;
    private Integer provinceId;
    private String provinceName;

    public static SeekerDtoOut from(Seeker seeker, String provinceName) {
        return SeekerDtoOut.builder().id(seeker.getId()).name(seeker.getName()).birthday(seeker.getBirthday())
                .address(seeker.getAddress()).provinceId(seeker.getProvince())
                .provinceName(provinceName).build();
    }

}
