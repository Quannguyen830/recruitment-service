package com.example.recruitment_service.repository.jpa;

import com.example.recruitment_service.model.JobField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobFieldRepository extends JpaRepository<JobField, Integer> {

}
