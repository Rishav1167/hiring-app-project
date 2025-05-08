package com.candidateonboardingsystem.utils.mapper;

import com.candidateonboardingsystem.utils.dtos.EducationalInfoDTO;
import com.candidateonboardingsystem.domain.entity.Candidate;
import com.candidateonboardingsystem.domain.entity.EducationalInfo;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EducationalInfoMapper {

    @Mapping(source = "candidateId", target = "candidate.id")
    EducationalInfo toEntity(EducationalInfoDTO dto);

    @Mapping(source = "candidate.id", target = "candidateId")
    EducationalInfoDTO toDto(EducationalInfo entity);


}