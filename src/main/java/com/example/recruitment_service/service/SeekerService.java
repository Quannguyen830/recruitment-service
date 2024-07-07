package com.example.recruitment_service.service;

import com.example.recruitment_service.dto.request.entity.PageDtoIn;
import com.example.recruitment_service.dto.request.entity.SeekerDtoIn;
import com.example.recruitment_service.dto.request.updateEntity.UpdatedSeekerDtoIn;
import com.example.recruitment_service.dto.response.PageDtoOut;
import com.example.recruitment_service.dto.response.SeekerDtoOut;

import java.math.BigInteger;

public interface SeekerService {
    SeekerDtoOut createSeeker(SeekerDtoIn seekerDtoIn);
    void updateSeeker(BigInteger id, UpdatedSeekerDtoIn updatedSeekerDtoIn);
    SeekerDtoOut findSeekerById(BigInteger id);
    PageDtoOut<SeekerDtoOut> findAllSeeker(PageDtoIn pageDtoIn);
    void deleteSeeker(BigInteger id);
}
