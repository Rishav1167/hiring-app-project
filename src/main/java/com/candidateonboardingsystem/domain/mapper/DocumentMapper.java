package com.candidateonboardingsystem.domain.mapper;


import com.candidateonboardingsystem.domain.entity.Document;
import com.candidateonboardingsystem.domain.dtos.DocumentDTO;

public class DocumentMapper {

    public static DocumentDTO toDTO(Document document) {
        return DocumentDTO.builder()
                .id(document.getId())
                .documentType(document.getDocumentType())
                .fileName(document.getFileName())
                .uploadedDate(document.getUploadedDate())
                .candidateId(document.getCandidate().getId())
                .build();
    }
}



