package com.candidateonboardingsystem.domain.dtos;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentDTO {
    private Long id;
    private String documentType;
    private String fileName;
    private String uploadedDate;
    private Long candidateId;
}
