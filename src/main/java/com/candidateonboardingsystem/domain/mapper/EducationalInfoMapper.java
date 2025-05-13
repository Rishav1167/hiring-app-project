package com.candidateonboardingsystem.domain.mapper;

import com.candidateonboardingsystem.domain.dtos.EducationalInfoDTO;
import com.candidateonboardingsystem.domain.entity.EducationalInfo;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EducationalInfoMapper {

    @Mapping(source = "candidateId", target = "candidate.id")
    EducationalInfo toEntity(EducationalInfoDTO dto);

    @Mapping(source = "candidate.id", target = "candidateId")
    EducationalInfoDTO toDto(EducationalInfo entity);


}