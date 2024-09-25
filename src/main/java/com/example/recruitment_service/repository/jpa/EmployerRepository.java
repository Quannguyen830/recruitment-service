package com.example.recruitment_service.repository.jpa;

import com.example.recruitment_service.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {
    Optional<Employer> findByEmail(String email);

    @Query("SELECT COUNT(e) FROM Employer e WHERE e.createdAt BETWEEN :startDate AND :endDate")
    Long countTotalEmployersByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT COUNT(e) FROM Employer e WHERE e.createdAt = :date")
    Long countEmployerByDate(@Param("date") LocalDate date);
}
