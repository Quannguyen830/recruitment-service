package com.example.recruitment_service.service.impl;

import com.example.recruitment_service.common.errorCode.ErrorCode;
import com.example.recruitment_service.common.exception.ApiException;
import com.example.recruitment_service.dto.dtoIn.entity.PageDtoIn;
import com.example.recruitment_service.dto.dtoIn.entity.SeekerDtoIn;
import com.example.recruitment_service.dto.dtoIn.updateEntity.UpdatedSeekerDtoIn;
import com.example.recruitment_service.dto.dtoOut.PageDtoOut;
import com.example.recruitment_service.dto.dtoOut.SeekerDtoOut;
import com.example.recruitment_service.model.JobProvince;
import com.example.recruitment_service.model.Seeker;
import com.example.recruitment_service.repository.JobProvinceRepository;
import com.example.recruitment_service.repository.SeekerRepository;
import com.example.recruitment_service.service.SeekerService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

    private final SeekerRepository seekerRepository;
    private final JobProvinceRepository provinceRepository;

    @Override
    public SeekerDtoOut createSeeker(SeekerDtoIn seekerDtoIn) {
        Seeker seeker = SeekerDtoIn.from(seekerDtoIn);
        seekerRepository.save(seeker);
        return SeekerDtoOut.from(seeker, getProvinceName(seeker.getProvince()));
    }

    @Override
    @CachePut(value = "seekers", key = "#id")
    public void updateSeeker(BigInteger id, UpdatedSeekerDtoIn updatedSeekerDtoIn) {
        Seeker seekerFound = seekerRepository.findById(id).orElseThrow(
                () -> new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Seeker not found")
        );

        seekerFound.setName(updatedSeekerDtoIn.getName());
        seekerFound.setAddress(updatedSeekerDtoIn.getAddress());
        seekerFound.setBirthday(updatedSeekerDtoIn.getBirthday());
        seekerFound.setProvince(updatedSeekerDtoIn.getProvinceId());
        seekerFound.setUpdated_at(LocalDate.now());
        seekerRepository.save(seekerFound);
    }

    @Override
    @Cacheable(value = "seekers", key = "#id")
    public SeekerDtoOut findSeekerById(BigInteger id) {
        Seeker seekerFound = seekerRepository.findById(id).orElseThrow(
                () -> new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Seeker not found")
        );

        return SeekerDtoOut.from(seekerFound, getProvinceName(seekerFound.getProvince()));
    }

    @Override
    @Cacheable(value = "seekers")
    public PageDtoOut<SeekerDtoOut> findAllSeeker(PageDtoIn pageDtoIn) {
        Pageable pageable = PageRequest.of(pageDtoIn.getPage()-1, pageDtoIn.getPageSize(),
                Sort.by("name").descending());

        Page<Seeker> page = seekerRepository.findAll(pageable);
        return PageDtoOut.from(page.getTotalPages(), page.getSize(), page.getTotalElements()
                , page.stream().map(
                        (seeker) -> SeekerDtoOut.from(seeker,
                                getProvinceName(seeker.getProvince()) == null ? null : getProvinceName(seeker.getProvince()))
                ).toList());
    }

    @Override
    @CacheEvict(value = "seekers", key = "#id")
    public void deleteSeeker(BigInteger id) {
        Seeker seeker = seekerRepository.findById(id).orElseThrow(
                () -> new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Seeker not found")
        );
        seekerRepository.delete(seeker);
    }

    private String getProvinceName(Integer id) {
        Optional<JobProvince> province = provinceRepository.findById(id);
        return province.map(JobProvince::getName).orElse(null);
    }
}
