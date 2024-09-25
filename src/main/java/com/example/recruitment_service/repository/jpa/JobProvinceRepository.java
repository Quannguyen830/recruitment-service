package com.example.recruitment_service.repository.jpa;

import com.example.recruitment_service.model.JobProvince;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobProvinceRepository extends JpaRepository<JobProvince, Integer> {

}
