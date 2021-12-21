package com.arikanogluulku.springfirst.converter;

import com.arikanogluulku.springfirst.dto.UserDto;
import com.arikanogluulku.springfirst.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    UserDto convertUserToUserDto(User user);

    User convertUserDtoToUser(UserDto userDto);

    List<UserDto> convertAllUserToUserDto(List<User> userList);
}