package com.candidateonboardingsystem.service;

import com.candidateonboardingsystem.domain.entity.Candidate;
import com.candidateonboardingsystem.domain.entity.Document;
import com.candidateonboardingsystem.repository.CandidateRepository;
import com.candidateonboardingsystem.repository.DocumentRepository;
import com.candidateonboardingsystem.utils.dtos.DocumentDTO;
import com.candidateonboardingsystem.utils.mapper.DocumentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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


    public ResponseEntity<byte[]> downloadDocument(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        String fileName = document.getFileName();

        MediaType mediaType = getMediaTypeForFileName(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .contentType(mediaType)
                .body(document.getFileData());
    }

    private MediaType getMediaTypeForFileName(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        switch (extension) {
            case "pdf":
                return MediaType.APPLICATION_PDF;
            case "png":
                return MediaType.IMAGE_PNG;
            case "jpg":
            case "jpeg":
                return MediaType.IMAGE_JPEG;
            case "doc":
                return MediaType.valueOf("application/msword");
            case "docx":
                return MediaType.valueOf("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            case "xls":
                return MediaType.valueOf("application/vnd.ms-excel");
            case "xlsx":
                return MediaType.valueOf("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            case "txt":
                return MediaType.TEXT_PLAIN;
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
    public Boolean checkIfAnyDocumentExists(Long candidateId) {
        boolean exists = documentRepository.existsByCandidateId(candidateId);
        log.info("Document exists for candidate {}: {}", candidateId, exists);
        return exists;
    }
}
