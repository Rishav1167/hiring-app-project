package com.candidateonboardingsystem.service;

import com.candidateonboardingsystem.domain.entity.Candidate;
import com.candidateonboardingsystem.domain.entity.Document;
import com.candidateonboardingsystem.repository.CandidateRepository;
import com.candidateonboardingsystem.repository.DocumentRepository;
import com.candidateonboardingsystem.utils.dtos.DocumentDTO;
import com.candidateonboardingsystem.utils.mapper.DocumentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    public List<DocumentDTO> uploadMultipleDocuments(String documentType, Long candidateId, List<MultipartFile> files) throws IOException {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        List<DocumentDTO> documentDTOs = new ArrayList<>();

        for (MultipartFile file : files) {
            // Check if an empty document row exists
            Document existingEmptyDoc = documentRepository
                    .findFirstByCandidateIdAndFileDataIsNull(candidateId)
                    .orElse(null);
            Document documentToSave;

            if (existingEmptyDoc != null) {
                // Update the existing empty document
                existingEmptyDoc.setDocumentType(documentType);
                existingEmptyDoc.setFileName(file.getOriginalFilename());
                existingEmptyDoc.setUploadedDate(LocalDate.now().toString());
                existingEmptyDoc.setFileData(file.getBytes());
                documentToSave = existingEmptyDoc;
            } else {
                // Create a new document
                documentToSave = Document.builder()
                        .documentType(documentType)
                        .fileName(file.getOriginalFilename())
                        .uploadedDate(LocalDate.now().toString())
                        .fileData(file.getBytes())
                        .candidate(candidate)
                        .build();
            }

            Document saved = documentRepository.save(documentToSave);
            documentDTOs.add(DocumentMapper.toDTO(saved));
        }

        return documentDTOs;
    }


    public byte[] downloadDocument(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        return document.getFileData();
    }

    public Boolean checkIfAnyDocumentExists(Long candidateId) {
        boolean exists = documentRepository.existsByCandidateId(candidateId);
        log.info("Document exists for candidate {}: {}", candidateId, exists);
        return exists;
    }
}
