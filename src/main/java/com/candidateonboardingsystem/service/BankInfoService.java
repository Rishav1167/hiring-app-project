package com.candidateonboardingsystem.service;


import com.candidateonboardingsystem.utils.dtos.BankInfoDTO;
import com.candidateonboardingsystem.domain.entity.BankInfo;
import com.candidateonboardingsystem.domain.entity.Candidate;
import com.candidateonboardingsystem.exceptions.ResourceNotFoundException;
import com.candidateonboardingsystem.utils.mapper.BankInfoMapper;
import com.candidateonboardingsystem.repository.BankInfoRepository;
import com.candidateonboardingsystem.repository.CandidateRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class BankInfoService {

    private final BankInfoRepository bankInfoRepository;
    private final CandidateRepository candidateRepository;
    private final BankInfoMapper bankInfoMapper;

    public BankInfoService(BankInfoRepository bankInfoRepository,
                           CandidateRepository candidateRepository,
                           BankInfoMapper bankInfoMapper) {
        this.bankInfoRepository = bankInfoRepository;
        this.candidateRepository = candidateRepository;
        this.bankInfoMapper = bankInfoMapper;
    }

    public BankInfoDTO saveBankInfo(BankInfoDTO dto) {
        Candidate candidate = candidateRepository.findById(dto.getCandidateId())
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));

        BankInfo bankInfo = bankInfoMapper.toEntity(dto);
        bankInfo.setCandidate(candidate);
        return bankInfoMapper.toDTO(bankInfoRepository.save(bankInfo));
    }

    public BankInfoDTO getBankInfoById(Long id) {
        BankInfo info = bankInfoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bank info not found"));
        return bankInfoMapper.toDTO(info);
    }
}

