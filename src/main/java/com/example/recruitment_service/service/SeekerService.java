package com.example.recruitment_service.service;

import com.example.recruitment_service.dto.dtoIn.entity.PageDtoIn;
import com.example.recruitment_service.dto.dtoIn.entity.SeekerDtoIn;
import com.example.recruitment_service.dto.dtoIn.updateEntity.UpdatedSeekerDtoIn;
import com.example.recruitment_service.dto.dtoOut.PageDtoOut;
import com.example.recruitment_service.dto.dtoOut.SeekerDtoOut;

import java.math.BigInteger;

public interface SeekerService {
    SeekerDtoOut add(SeekerDtoIn seekerDtoIn);
    void update(BigInteger id, UpdatedSeekerDtoIn updatedSeekerDtoIn);
    SeekerDtoOut get(BigInteger id);
    PageDtoOut<SeekerDtoOut> list(PageDtoIn pageDtoIn);
    void delete(BigInteger id);
}
