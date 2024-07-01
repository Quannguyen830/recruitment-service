package com.example.recruitment_service.service.impl;

import com.example.recruitment_service.common.errorCode.ErrorCode;
import com.example.recruitment_service.common.exception.ApiException;
import com.example.recruitment_service.dto.dtoIn.PageDtoIn;
import com.example.recruitment_service.dto.dtoIn.SeekerDtoIn;
import com.example.recruitment_service.dto.dtoIn.UpdatedSeekerDtoIn;
import com.example.recruitment_service.dto.dtoOut.PageDtoOut;
import com.example.recruitment_service.dto.dtoOut.SeekerDtoOut;
import com.example.recruitment_service.model.JobProvince;
import com.example.recruitment_service.model.Seeker;
import com.example.recruitment_service.repository.JobProvinceRepository;
import com.example.recruitment_service.repository.SeekerRepository;
import com.example.recruitment_service.service.SeekerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeekerServiceImpl implements SeekerService {

    private SeekerRepository seekerRepository;
    private JobProvinceRepository provinceRepository;

    @Override
    public SeekerDtoOut createSeeker(SeekerDtoIn seekerDtoIn) {
        Seeker seeker = SeekerDtoIn.from(seekerDtoIn);
        seekerRepository.save(seeker);
        return SeekerDtoOut.from(seeker, getProvinceName(seeker.getProvince()));
    }

    @Override
    public void updateSeeker(BigInteger id, UpdatedSeekerDtoIn updatedSeekerDtoIn) {
        Seeker seekerFound = seekerRepository.findById(id).orElseThrow(
                () -> new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "seeker not found")
        );

        seekerFound.setName(updatedSeekerDtoIn.getName());
        seekerFound.setAddress(updatedSeekerDtoIn.getAddress());
        seekerFound.setBirthday(updatedSeekerDtoIn.getBirthday());
        seekerFound.setProvince(updatedSeekerDtoIn.getProvinceId());
        seekerFound.setUpdated_at(LocalDate.now());
        seekerRepository.save(seekerFound);
    }

    @Override
    public SeekerDtoOut findSeekerById(BigInteger id) {
        Seeker seekerFound = seekerRepository.findById(id).orElseThrow(
                () -> new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "seeker not found")
        );

        return SeekerDtoOut.from(seekerFound, getProvinceName(seekerFound.getProvince()));
    }

    @Override
    public PageDtoOut<SeekerDtoOut> findAllSeeker(PageDtoIn pageDtoIn) {
        Pageable pageable = PageRequest.of(pageDtoIn.getPage()-1, pageDtoIn.getPageSize(),
                Sort.by("name").ascending());
        Page<Seeker> page = seekerRepository.findAllByOrderByNameAsc(pageable);
        return PageDtoOut.from(page.getTotalPages(), page.getSize(), page.getTotalElements()
                , page.stream().map(
                        (seeker) -> SeekerDtoOut.from(seeker, getProvinceName(seeker.getProvince()))
                ).toList());
    }

    @Override
    public void deleteSeeker(BigInteger id) {
        Seeker seeker = seekerRepository.findById(id).orElseThrow(
                () -> new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "seeker not found")
        );
        seekerRepository.delete(seeker);
    }

    private String getProvinceName(Integer id) {
        Optional<JobProvince> province = provinceRepository.findById(id);
        return province.map(JobProvince::getName).orElseThrow(
                () -> new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "province not found")
        );
    }
}
