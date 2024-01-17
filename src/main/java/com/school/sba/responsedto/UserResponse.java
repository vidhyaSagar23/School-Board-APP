package com.school.sba.responsedto;

import org.springframework.stereotype.Component;

import com.school.sba.enumuration.UserRole;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	
	private int userId;
	private String userName;
	private String userFirstName;
	private String userLastName;
	private long userContactNo;
	private String userEmail;
	private UserRole userRole;
}
