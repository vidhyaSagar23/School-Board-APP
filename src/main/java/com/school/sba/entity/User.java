package com.school.sba.entity;

import org.springframework.stereotype.Component;

import com.school.sba.enumuration.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Component
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@Column(unique = true)
	private String userName;
	private String userPassword;
	private String userFirstName;
	private String userLastName;
	@Column(unique = true)
	private long userContactNo;
	@Column(unique = true)
	private String userEmail;
	private UserRole userRole;
	private boolean isDeleted;
	
	@ManyToOne
	private School school;
}
