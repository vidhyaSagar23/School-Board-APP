package com.school.sba.requestdto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class SchoolRequest {
	@NotNull(message = "School name cannot be null")
	@Pattern(regexp = "^[A-Z][a-zA-Z]*( [A-Z][a-zA-Z]*)?$", 
			message = "School name should start with an uppercase letter, and if there are two names, the second name should start with an uppercase letter.")
	private String schoolName;
	private long contactNo;
	@Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}", message = "invalid email ")
	private String emailId;
	@NotBlank(message ="School Address cannot be blank")
	private String address;
}
