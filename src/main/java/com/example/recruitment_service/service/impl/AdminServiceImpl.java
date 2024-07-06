package com.example.recruitment_service.service.impl;

import com.example.recruitment_service.common.errorCode.ErrorCode;
import com.example.recruitment_service.common.exception.ApiException;
import com.example.recruitment_service.dto.dtoIn.filter.FilterByDayDtoIn;
import com.example.recruitment_service.dto.dtoOut.FilterByDayDtoOut;
import com.example.recruitment_service.dto.dtoOut.FilterByJobDtoOut;
import com.example.recruitment_service.model.*;
import com.example.recruitment_service.repository.*;
import com.example.recruitment_service.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final EmployerRepository employerRepository;
    private final JobRepository jobRepository;
    private final ResumeRepository resumeRepository;
    private final SeekerRepository seekerRepository;
    private final JobFieldRepository jobFieldRepository;
    private final JobProvinceRepository jobProvinceRepository;

    @Override
    public FilterByDayDtoOut filterByDay(FilterByDayDtoIn filterByDayDtoIn) {
        LocalDate fromDate = LocalDate.parse(filterByDayDtoIn.getFromDate());
        LocalDate toDate = LocalDate.parse(filterByDayDtoIn.getToDate());
        LocalDate currentDate = fromDate;
        List<Element> elements = new ArrayList<>();

        while(!currentDate.isAfter(toDate)) {
            Element element = new Element(currentDate,
                    employerRepository.countEmployerByDate(currentDate),
                    jobRepository.countJobByDate(currentDate),
                    seekerRepository.countSeekerByDate(currentDate),
                    resumeRepository.countResumeByDate(currentDate));
            elements.add(element);
            currentDate = currentDate.plusDays(1);
        }

        return FilterByDayDtoOut.builder()
                .numEmployer(employerRepository.countTotalEmployersByDateRange(fromDate, toDate))
                .numJob(jobRepository.countTotalJobsByDateRange(fromDate, toDate))
                .numSeeker(seekerRepository.countTotalSeekerByDateRange(fromDate, toDate))
                .numResume(resumeRepository.countTotalResumesByDateRange(fromDate, toDate))
                .chart(elements)
                .build();
    }

    public FilterByJobDtoOut findMatchingJobAndSeeker(BigInteger id) {
        Job job = jobRepository.findById(id).orElseThrow(
                () -> new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Job not found")
        );

        Map<Integer, String> fields = new HashMap<>();
        Map<Integer, String> provinces = new HashMap<>();
        String[] provinceIds = job.getProvinces().split("-");
        String[] fieldIds = job.getFields().split("-");
        List<String> provinceIdList = Arrays.stream(provinceIds).toList();
        List<String> fieldIdList = Arrays.stream(fieldIds).toList();

        for(int i=1; i<fieldIds.length; i++) {
            Integer fieldId = Integer.valueOf(fieldIds[i]);
            fields.put(fieldId, jobFieldRepository.findById(fieldId).orElseThrow(
                    () -> new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Field not found")
            ).getName());
        }

        for(int i=1; i<provinceIds.length; i++) {
            Integer provinceId = Integer.valueOf(provinceIds[i]);
            provinces.put(provinceId, jobProvinceRepository.findById(provinceId).orElseThrow(
                    () -> new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Province not found")
            ).getName());
        }

        Employer employer = employerRepository.findById(job.getEmployerId()).orElseThrow(
                () -> new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Employer not found")
        );

        Map<BigInteger, String> seekers = new HashMap<>();
        List<Seeker> seekerList = seekerRepository.findMatchingSeekers(job.getSalary(),
                fieldIdList, provinceIdList);
        for(Seeker seeker: seekerList) {
            seekers.put(seeker.getId(), seeker.getName());
        }

        return FilterByJobDtoOut.builder().id(job.getId()).title(job.getTitle())
                .quantity(job.getQuantity()).fields(fields).provinces(provinces).salary(job.getSalary())
                .expiredAt(job.getExpired_at()).employerId(job.getEmployerId())
                .employerName(employer.getName()).seekers(seekers).build();
    }
}
