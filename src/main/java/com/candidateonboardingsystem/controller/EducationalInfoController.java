package com.candidateonboardingsystem.controller;

import com.candidateonboardingsystem.service.EducationalInfoService;
import com.candidateonboardingsystem.domain.dtos.EducationalInfoDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/educational-info")
public class EducationalInfoController {

    private final EducationalInfoService educationalInfoService;

    public EducationalInfoController(EducationalInfoService educationalInfoService) {
        this.educationalInfoService = educationalInfoService;
    }

    @PostMapping
    public ResponseEntity<EducationalInfoDTO> createEducationalInfo(@Valid @RequestBody EducationalInfoDTO dto) {
        EducationalInfoDTO saved = educationalInfoService.saveEducationalInfo(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationalInfoDTO> getEducationalInfoById(@PathVariable Long id) {
        EducationalInfoDTO info = educationalInfoService.getById(id);
        return ResponseEntity.ok(info);
    }
}
