package com.candidateonboardingsystem.controller;

import com.candidateonboardingsystem.domain.entity.Document;
import com.candidateonboardingsystem.repository.DocumentRepository;
import com.candidateonboardingsystem.service.DocumentService;
import com.candidateonboardingsystem.utils.dtos.DocumentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private DocumentRepository documentRepository;

    @PostMapping("/upload-multiple")
    public ResponseEntity<?> uploadMultipleDocuments(
            @RequestParam("documentType") String documentType,
            @RequestParam("candidateId") Long candidateId,
            @RequestParam("files") List<MultipartFile> files
    ) {
        try {
            List<DocumentDTO> documents = documentService.uploadMultipleDocuments(documentType, candidateId, files);
            return ResponseEntity.ok(documents);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadDocument(@PathVariable Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        String fileName = document.getFileName();

        // Detect content type based on file extension
        MediaType mediaType = getMediaTypeForFileName(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .contentType(mediaType)
                .body(document.getFileData());
    }

    private MediaType getMediaTypeForFileName(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        switch (extension) {
            case "pdf": return MediaType.APPLICATION_PDF;
            case "png": return MediaType.IMAGE_PNG;
            case "jpg":
            case "jpeg": return MediaType.IMAGE_JPEG;
            case "doc": return MediaType.valueOf("application/msword");
            case "docx": return MediaType.valueOf("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            case "xls": return MediaType.valueOf("application/vnd.ms-excel");
            case "xlsx": return MediaType.valueOf("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            case "txt": return MediaType.TEXT_PLAIN;
            default: return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    @GetMapping("/check/{candidateId}")
    public ResponseEntity<Boolean> checkDocument(@PathVariable Long candidateId) {
        return new ResponseEntity<>(documentService.checkIfAnyDocumentExists(candidateId), HttpStatus.OK);
    }

}
