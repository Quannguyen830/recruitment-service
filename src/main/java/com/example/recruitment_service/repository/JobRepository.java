package com.example.recruitment_service.repository;

import com.example.recruitment_service.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface JobRepository extends JpaRepository<Job, BigInteger> {
    @Query("SELECT j FROM Job j JOIN Employer e ON j.employerId = e.id ORDER BY j.expired_at DESC, e.name ASC")
    Page<Job> findAllJobsSorted(Pageable pageable);
}
