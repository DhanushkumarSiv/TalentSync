package com.web.userservice.mapper;

import com.web.userservice.dto.RegisterResponseDto;
import com.web.userservice.dto.SkillResponseDto;
import com.web.userservice.dto.UserResponseDto;
import com.web.userservice.entity.UserSkill;
import com.web.userservice.entity.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityToDto {

    RegisterResponseDto toRegisterDto(Users user);
    UserResponseDto toUserDto(Users user);
    SkillResponseDto toSkillDto(UserSkill userSkill);
}
