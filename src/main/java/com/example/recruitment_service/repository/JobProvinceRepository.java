package com.example.recruitment_service.repository;

import com.example.recruitment_service.model.JobProvince;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface JobProvinceRepository extends JpaRepository<JobProvince, Integer> {

}
