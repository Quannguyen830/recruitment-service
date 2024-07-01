package com.example.recruitment_service.repository;

import com.example.recruitment_service.dto.dtoIn.PageDtoIn;
import com.example.recruitment_service.model.Seeker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface SeekerRepository extends JpaRepository<Seeker, BigInteger> {
    @Query("SELECT s FROM Seeker s ORDER BY s.name ASC")
    Page<Seeker> findAllByOrderByNameAsc(Pageable pageable);
}
