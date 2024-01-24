package com.school.sba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.sba.requestdto.UserRequest;
import com.school.sba.responsedto.UserResponse;
import com.school.sba.service.UserService;
import com.school.sba.util.ResponseStructure;

import jakarta.validation.Valid;



@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("users/register")
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(@RequestBody @Valid UserRequest userRequest) {
		return userService.saveUser(userRequest);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")    // USED TO GIVE AUTHORIZATION FOR THIS PARTICULAR METHOD FOR ADMIN
	@DeleteMapping("users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(@PathVariable int userId){
		return userService.deleteUser(userId);
	}
	
	@GetMapping("users/{userId}")
	@PreAuthorize("hasAuthority('TEACHER') OR hasAuthority('ADMIN') OR hasAuthority('STUDENT')")
	public ResponseEntity<ResponseStructure<UserResponse>> findById(@PathVariable int userId){
		return userService.findUserById(userId);
	}
	
}
