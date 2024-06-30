package com.example.recruitment_service.service.impl;

import com.example.recruitment_service.dto.dtoIn.PageDtoIn;
import com.example.recruitment_service.dto.dtoIn.SeekerDtoIn;
import com.example.recruitment_service.dto.dtoOut.PageDtoOut;
import com.example.recruitment_service.dto.dtoOut.SeekerDtoOut;
import com.example.recruitment_service.model.Seeker;
import com.example.recruitment_service.repository.SeekerRepository;
import com.example.recruitment_service.service.SeekerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class SeekerServiceImpl implements SeekerService {

    private SeekerRepository seekerRepository;

    @Override
    public SeekerDtoOut createSeeker(SeekerDtoIn seekerDtoIn) {
        Seeker seeker = SeekerDtoIn.from(seekerDtoIn);
        seekerRepository.save(seeker);
        return SeekerDtoOut.from(seeker, new String());
    }

    @Override
    public void updateSeeker(SeekerDtoIn seekerDtoIn) {

    }

    @Override
    public SeekerDtoOut findSeekerById(BigInteger id) {
        return null;
    }

    @Override
    public PageDtoOut<SeekerDtoOut> findAllSeeker(PageDtoIn pageDtoIn) {
        return null;
    }

    @Override
    public void deleteSeeker(BigInteger id) {

    }
}
