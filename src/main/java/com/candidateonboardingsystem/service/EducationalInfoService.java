package com.candidateonboardingsystem.service;

import com.candidateonboardingsystem.domain.entity.Candidate;
import com.candidateonboardingsystem.domain.entity.EducationalInfo;
import com.candidateonboardingsystem.exceptions.ResourceNotFoundException;
import com.candidateonboardingsystem.repository.CandidateRepository;
import com.candidateonboardingsystem.repository.EducationalInfoRepository;
import com.candidateonboardingsystem.domain.dtos.EducationalInfoDTO;
import com.candidateonboardingsystem.domain.mapper.EducationalInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class EducationalInfoService {

    private final EducationalInfoRepository repository;
    private final CandidateRepository candidateRepository;
    private final EducationalInfoMapper mapper;

    public EducationalInfoService(
            EducationalInfoRepository repository,
            CandidateRepository candidateRepository,
            EducationalInfoMapper mapper) {
        this.repository = repository;
        this.candidateRepository = candidateRepository;
        this.mapper = mapper;
    }

    public EducationalInfoDTO saveEducationalInfo(EducationalInfoDTO dto) {
        Candidate candidate = candidateRepository.findById(dto.getCandidateId())
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));

        EducationalInfo entity = mapper.toEntity(dto);
        entity.setCandidate(candidate);

        return mapper.toDto(repository.save(entity));
    }

    public EducationalInfoDTO getById(Long id) {
        EducationalInfo info = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Educational info not found"));

        return mapper.toDto(info);
    }
}
