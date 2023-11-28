package com.net.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "User dto model ", name = "User DTO for User class")
public class UserDto {

	private Long id;
	
	@Schema(description = "First name of the user")
	@NotEmpty(message = "first name should not be empty")
	private String firstName;
	
	@Schema(description = "last name of the user")
	@NotEmpty(message = "last name should not be empty")
	private String lastName;
	
	
	@Schema(description = "email of the user")
	@NotEmpty(message = "email should not be empty")
	@Email(message = "Email id is not valid")
	private String email;
}
