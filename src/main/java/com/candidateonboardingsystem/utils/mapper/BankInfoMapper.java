package com.candidateonboardingsystem.utils.mapper;


import com.candidateonboardingsystem.utils.dtos.BankInfoDTO;
import com.candidateonboardingsystem.domain.entity.BankInfo;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BankInfoMapper {

    @Mapping(source = "candidateId", target = "candidate.id")
    BankInfo toEntity(BankInfoDTO dto);

    @Mapping(source = "candidate.id", target = "candidateId")
    BankInfoDTO toDTO(BankInfo entity);
}
