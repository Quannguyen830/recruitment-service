package com.example.recruitment_service.service;

import com.example.recruitment_service.dto.dtoIn.PageDtoIn;
import com.example.recruitment_service.dto.dtoIn.SeekerDtoIn;
import com.example.recruitment_service.dto.dtoOut.PageDtoOut;
import com.example.recruitment_service.dto.dtoOut.SeekerDtoOut;
import org.springframework.data.domain.Pageable;

import java.math.BigInteger;

public interface SeekerService {
    SeekerDtoOut createSeeker(SeekerDtoIn seekerDtoIn);
    void updateSeeker(SeekerDtoIn seekerDtoIn);
    SeekerDtoOut findSeekerById(BigInteger id);
    PageDtoOut<SeekerDtoOut> findAllSeeker(PageDtoIn pageDtoIn);
    void deleteSeeker(BigInteger id);
}
