package com.net.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.net.dto.UserDto;
import com.net.entity.User;

@Mapper
public interface MapStructMapper {

	MapStructMapper Mapper = Mappers.getMapper(MapStructMapper.class);
	
	@Mapping(source = "id",target = "id")
	UserDto mapToUserDto(User user);
	
	User mapToUser(UserDto userDto);
}
