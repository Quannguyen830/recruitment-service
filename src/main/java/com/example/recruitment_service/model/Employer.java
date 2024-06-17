package com.example.recruitment_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Employer")
public class Employer {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "Email", unique = true, nullable = false)
    private String email;

    @Column(name = "Name")
    private String name;

    private Integer province;

    private String province_name;

    @Column(name = "Description")
    private String description;

    @Builder.Default
    @Column(name = "Created at")
    private LocalDate createdAt = LocalDate.now();

    @Builder.Default
    @Column(name = "Updated at")
    private LocalDate updatedAt = LocalDate.now();

    public Employer(Long id, String email, String name, Integer province, String description, LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.province = province;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}

