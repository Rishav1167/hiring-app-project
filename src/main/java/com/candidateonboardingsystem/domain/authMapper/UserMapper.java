package com.candidateonboardingsystem.domain.authMapper;

import com.candidateonboardingsystem.domain.authDto.RegisterRequest;
import com.candidateonboardingsystem.domain.authEntity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UserMapperHelper.class)
public interface UserMapper {

    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    User toUser(RegisterRequest registerRequest);
}