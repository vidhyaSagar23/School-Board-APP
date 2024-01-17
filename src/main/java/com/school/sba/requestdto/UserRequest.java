package com.school.sba.requestdto;

import org.springframework.stereotype.Component;

import com.school.sba.enumuration.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserRequest {
	
	@NotNull(message = "Username cannot be null")
	@Pattern(regexp = "^[A-Z][a-zA-Z]*( [A-Z][a-zA-Z]*)?$", 
	message = "Username should start with an uppercase letter, and if there are two names, the second name should start with an uppercase letter.")
	private String userName;

	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must"
			+ " contain at least one letter, one number, one special character")
	private String userPassword;
	private String userFirstName;
	private String userLastName;
	private long userContactNo;
	@NotBlank(message ="email cannot be blank")
	@Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}", message = "invalid email ")
	private String userEmail;
	private UserRole userRole;

}
