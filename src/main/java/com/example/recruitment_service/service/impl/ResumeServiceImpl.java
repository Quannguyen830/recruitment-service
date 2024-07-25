package com.example.recruitment_service.service.impl;

import com.example.recruitment_service.common.errorCode.ErrorCode;
import com.example.recruitment_service.common.exception.ApiException;
import com.example.recruitment_service.dto.dtoIn.entity.PageDtoIn;
import com.example.recruitment_service.dto.dtoIn.entity.ResumeDtoIn;
import com.example.recruitment_service.dto.dtoIn.updateEntity.UpdatedResumeDtoIn;
import com.example.recruitment_service.dto.dtoOut.PageDtoOut;
import com.example.recruitment_service.dto.dtoOut.ResumeDtoOut;
import com.example.recruitment_service.model.Resume;
import com.example.recruitment_service.model.Seeker;
import com.example.recruitment_service.repository.JobFieldRepository;
import com.example.recruitment_service.repository.JobProvinceRepository;
import com.example.recruitment_service.repository.ResumeRepository;
import com.example.recruitment_service.repository.SeekerRepository;
import com.example.recruitment_service.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final SeekerRepository seekerRepository;
    private final JobFieldRepository jobFieldRepository;
    private final JobProvinceRepository jobProvinceRepository;

    @Override
    @CachePut(cacheNames = "resumes", key = "#result.id")
    public ResumeDtoOut add(ResumeDtoIn resumeDtoIn) {
        Resume resume = ResumeDtoIn.from(resumeDtoIn);
        resumeRepository.save(resume);
        return getResumeDtoOut(resume);
    }

    @Override
    @CachePut(cacheNames = "resumes", key = "#id")
    public void update(BigInteger id, UpdatedResumeDtoIn updatedResumeDtoIn) {
        Resume resume = resumeRepository.findById(id).orElseThrow(
                () -> new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Resume not found")
        );

        resume.setTitle(updatedResumeDtoIn.getTitle());
        resume.setCareer_obj(updatedResumeDtoIn.getCareerObj());
        resume.setProvinces(updatedResumeDtoIn.getProvinceIds());
        resume.setFields(updatedResumeDtoIn.getFieldIds());
        resume.setUpdated_at(LocalDate.now());
        resumeRepository.save(resume);
    }

    @Override
    @Cacheable(cacheNames = "resumes", key = "#id")
    public ResumeDtoOut get(BigInteger id) {
        Resume resume = resumeRepository.findById(id).orElseThrow(
                () -> new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Resume not found")
        );
        return getResumeDtoOut(resume);
    }

    @Override
    @Cacheable(cacheNames = "resumes", key = "'page=' + #pageDtoIn.page + ',sort=id'")
    public PageDtoOut<ResumeDtoOut> list(PageDtoIn pageDtoIn) {
        Pageable pageable = PageRequest.of(pageDtoIn.getPage()-1, pageDtoIn.getPageSize());
        Page<Resume> page = resumeRepository.findAllResumeSorted(pageable);
        return PageDtoOut.from(page.getTotalPages(), page.getSize(), page.getTotalElements(),
                page.stream().map(this::getResumeDtoOut).toList());
    }

    @Override
    @CacheEvict(cacheNames = "resumes", key = "#id")
    public void delete(BigInteger id) {
        Resume resume = resumeRepository.findById(id).orElseThrow(
                () -> new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Resume not found")
        );
        resumeRepository.delete(resume);
    }

    private HashMap<Integer, String> getFields(String list) {
        HashMap<Integer, String> fields = new HashMap<>();
        String[] fieldIds = list.split("-");
        for(int i=1; i<fieldIds.length; i++) {
            Integer fieldIdValue = Integer.valueOf(fieldIds[i]);
            fields.put(fieldIdValue, jobFieldRepository.findById(fieldIdValue).orElseThrow(
                    () -> new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Field not found")
            ).getName());
        }
        return fields;
    }

    private HashMap<Integer, String> getProvinces(String list) {
        HashMap<Integer, String> provinces = new HashMap<>();
        String[] provinceIds = list.split("-");
        for(int i=1; i<provinceIds.length; i++) {
            Integer provinceIdValue = Integer.valueOf(provinceIds[i]);
            provinces.put(provinceIdValue, jobProvinceRepository.findById(provinceIdValue).orElseThrow(
                    () -> new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Province not found")
            ).getName());
        }
        return provinces;
    }

    private ResumeDtoOut getResumeDtoOut(Resume resume) {
        Seeker seeker = seekerRepository.findById(resume.getSeeker_id()).orElseThrow(
                () -> new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Seeker not found")
        );
        HashMap<Integer, String> fields = getFields(resume.getFields());
        HashMap<Integer, String> provinces = getProvinces(resume.getProvinces());
        return ResumeDtoOut.from(resume, seeker.getName(), fields, provinces);
    }
}
