package com.web.userservice.mapper;

import com.web.userservice.dto.RegisterRequestDto;
import com.web.userservice.entity.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DtoToEntity {

    Users toUsers(RegisterRequestDto registerRequestDto);
}
