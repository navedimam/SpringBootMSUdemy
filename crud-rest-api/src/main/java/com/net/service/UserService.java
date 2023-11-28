package com.net.service;

import java.util.List;

import com.net.dto.UserDto;
import com.net.entity.User;

public interface UserService {

	UserDto createuser(UserDto userdto);
	
	UserDto getUserById(Long id);
	
	List<UserDto> getAllUsers();
	
	UserDto updateUser(UserDto userdto);
	
	void deleteuser(Long userId);
}
