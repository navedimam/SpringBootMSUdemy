package com.net.mapper;

import com.net.dto.UserDto;
import com.net.entity.User;

public class UserMapper {

	public static User userDtotoUser(UserDto dto) {
		
		return new User(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getEmail());
	}
	
	public static UserDto usertoUserDto(User user) {
		
		return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
	}
}
