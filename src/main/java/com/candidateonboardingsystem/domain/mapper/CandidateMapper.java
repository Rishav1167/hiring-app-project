package com.candidateonboardingsystem.domain.mapper;

import com.candidateonboardingsystem.domain.entity.Candidate;
import com.candidateonboardingsystem.domain.enums.CandidateStatus;
import com.candidateonboardingsystem.domain.dtos.CandidateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CandidateMapper {

    @Mapping(source = "status", target = "status", qualifiedByName = "mapStatusToEnum")
    Candidate toEntity(CandidateDTO dto);

    @Mapping(source = "status", target = "status", qualifiedByName = "mapStatusToString")
    CandidateDTO toDto(Candidate candidate);

    @Named("mapStatusToEnum")
    default CandidateStatus mapStatusToEnum(String status) {
        return status == null ? null : CandidateStatus.valueOf(status);
    }

    @Named("mapStatusToString")
    default String mapStatusToString(CandidateStatus status) {
        return status == null ? null : status.name();
    }
}
