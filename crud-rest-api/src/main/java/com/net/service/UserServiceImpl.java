package com.net.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.net.dto.UserDto;
import com.net.entity.User;
import com.net.exception.EmailNotFoundException;
import com.net.exception.ResourceNotFoundException;
import com.net.mapper.MapStructMapper;
import com.net.mapper.UserMapper;
import com.net.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

	// As of after spring 2.3 spring automatically inject beans
	// if it has only one parameterized constructor
	// So it dosent need @autowired annotation
	
	private UserRepository userRepository;
	
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createuser(UserDto userdto) {
		
		Optional<User> optionaluser = userRepository.findByEmail(userdto.getEmail());
		if(optionaluser.isPresent()) {
			throw new EmailNotFoundException("Email already exists for user.");
		}
		
		
		//using my custom mapper
//		User user = UserMapper.userDtotoUser(userdto);
		
		//using modelmapper
//		User user = modelMapper.map(userdto, User.class);
		
		//using mapstruct
		User user = MapStructMapper.Mapper.mapToUser(userdto);

		User savedUser = userRepository.save(user);
		
//		return UserMapper.usertoUserDto(savedUser); 
		return MapStructMapper.Mapper.mapToUserDto(savedUser);
	}

	@Override
	public UserDto getUserById(Long id) {
		
		User user = userRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("User", "id", id)
				);
		//ussing custom mapper
//		UserDto userreturn = UserMapper.usertoUserDto(user.get());
		
		// using modelmapper
//		UserDto userreturn = modelMapper.map(user.get(), UserDto.class);
		
		//using mapstruct
		UserDto userreturn = MapStructMapper.Mapper.mapToUserDto(user);
		
		return userreturn;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> list = userRepository.findAll();
		
//		ussing custom mapper
//		return list.stream().map(UserMapper::usertoUserDto)
//					.collect(Collectors.toList());
		
		//Using modelmapper
//		return list.stream().map((u)-> modelMapper.map(u, UserDto.class))
//				.collect(Collectors.toList());
		
		//using mapstruct
		return list.stream().map(MapStructMapper.Mapper::mapToUserDto)
				.collect(Collectors.toList());
	}

	@Override
	public UserDto updateUser(UserDto user) {
		
		User existingUser = userRepository.findById(user.getId()).orElseThrow(
				()-> new ResourceNotFoundException("User", "id", user.getId())
				);
		
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		
		User updatedUser = userRepository.save(existingUser);
//		ussing custom mapper
//		return UserMapper.usertoUserDto(updatedUser);
		
//		//using modelmapper
//		return modelMapper.map(updatedUser,UserDto.class);
		
		//using mapstruct
		return MapStructMapper.Mapper.mapToUserDto(updatedUser);
	}

	@Override
	public void deleteuser(Long userId) {
		userRepository.findById(userId).orElseThrow(
				()-> new ResourceNotFoundException("User", "id", userId)
				);
		
		userRepository.deleteById(userId);
		
	}

}
