package com.candidateonboardingsystem.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documentType;

    private String fileName;

    private String uploadedDate;

    @Lob
    @Column(name = "file_data", columnDefinition = "LONGBLOB") // For MySQL; use BYTEA for PostgreSQL
    private byte[] fileData;

    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;
}
