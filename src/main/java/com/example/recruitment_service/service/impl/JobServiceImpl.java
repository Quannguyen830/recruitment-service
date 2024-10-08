package com.example.recruitment_service.service.impl;

import com.example.recruitment_service.dto.dtoIn.entity.JobDtoIn;
import com.example.recruitment_service.dto.dtoIn.entity.PageDtoIn;
import com.example.recruitment_service.dto.dtoIn.updateEntity.UpdatedJobDtoIn;
import com.example.recruitment_service.dto.dtoOut.JobDtoOut;
import com.example.recruitment_service.dto.dtoOut.PageDtoOut;
import com.example.recruitment_service.common.errorCode.ErrorCode;
import com.example.recruitment_service.common.exception.ApiException;
import com.example.recruitment_service.model.Employer;
import com.example.recruitment_service.model.Job;
import com.example.recruitment_service.repository.jpa.EmployerRepository;
import com.example.recruitment_service.repository.jpa.JobFieldRepository;
import com.example.recruitment_service.repository.jpa.JobProvinceRepository;
import com.example.recruitment_service.repository.jpa.JobRepository;
import com.example.recruitment_service.service.JobService;
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
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final EmployerRepository employerRepository;
    private final JobProvinceRepository provinceRepository;
    private final JobFieldRepository fieldRepository;

    @Override
    @CachePut(cacheNames = "jobs", key = "#result.id")
    public JobDtoOut add(JobDtoIn jobDtoIn) {
        Job job = JobDtoIn.from(jobDtoIn);
        jobRepository.save(job);
        return getJobDtoOut(job);
    }

    @Override
    @CachePut(cacheNames = "jobs", key = "#id")
    public void update(BigInteger id, UpdatedJobDtoIn updatedJobDtoIn) {
        Job job = jobRepository.findById(id).orElseThrow(
                () -> new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Job not found")
        );

        job.setId(updatedJobDtoIn.getId());
        job.setTitle(updatedJobDtoIn.getTitle());
        job.setQuantity(updatedJobDtoIn.getQuantity());
        job.setDescription((updatedJobDtoIn.getDescription()));
        job.setFields(updatedJobDtoIn.getFieldIds());
        job.setProvinces(updatedJobDtoIn.getProvinceIds());
        job.setSalary(updatedJobDtoIn.getSalary());
        job.setExpired_at((updatedJobDtoIn.getExpiredAt()));
        job.setUpdated_at(LocalDate.now());
        jobRepository.save(job);
    }

    @Override
    @Cacheable(cacheNames = "jobs", key = "#id")
    public JobDtoOut get(BigInteger id) {
        Job job = jobRepository.findById(id).orElseThrow(
                () -> new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Job not found")
        );
        return getJobDtoOut(job);
    }

    @Override
    @Cacheable(cacheNames = "jobs", key = "'page=' + #pageDtoIn.page + ',sort=id'")
    public PageDtoOut<JobDtoOut> list(PageDtoIn pageDtoIn) {
        Pageable pageable = PageRequest.of(pageDtoIn.getPage()-1, pageDtoIn.getPageSize());
        Page<Job> page = jobRepository.findAllJobsSorted(pageable);
        return PageDtoOut.from(page.getTotalPages(), page.getSize(), page.getTotalElements()
                , page.stream().map(this::getJobDtoOut).toList());
    }

    @Override
    @CacheEvict(cacheNames = "jobs", key = "#id")
    public void delete(BigInteger id) {
        if(jobRepository.findById(id).isPresent()) {
            jobRepository.deleteById(id);
        } else {
            throw new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Job not found");
        }
    }

    private HashMap<Integer, String> getProvinces(String jobProvince) {
        HashMap<Integer, String> provinces = new HashMap<>();
        String[] provinceIds = jobProvince.split("-");
        for(int i=1; i<provinceIds.length; i++) {
            Integer provinceIdValue = Integer.valueOf(provinceIds[i]);
            provinces.put(provinceIdValue, provinceRepository.findById(provinceIdValue).orElseThrow(
                    () -> new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Province not found")
            ).getName());
        }
        return provinces;
    }

    private HashMap<Integer, String> getFields(String jobField) {
        HashMap<Integer, String> fields = new HashMap<>();
        String[] fieldIds = jobField.split("-");
        for(int i=1; i<fieldIds.length; i++) {
            Integer fieldIdValue = Integer.valueOf(fieldIds[i]);
            fields.put(fieldIdValue, fieldRepository.findById(fieldIdValue).orElseThrow(
                    () -> new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Field not found")
            ).getName());
        }
        return fields;
    }

    private JobDtoOut getJobDtoOut(Job job) {
        Employer employer = employerRepository.findById(job.getEmployerId()).orElseThrow(
                () -> new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Employer not found")
        );
        HashMap<Integer, String> fields = getFields(job.getFields());
        HashMap<Integer, String> provinces = getProvinces(job.getProvinces());
        return JobDtoOut.from(job, fields, provinces, employer.getName());
    }
}
