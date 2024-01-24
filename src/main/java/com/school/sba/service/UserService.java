package com.school.sba.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.school.sba.requestdto.UserRequest;
import com.school.sba.responsedto.UserResponse;
import com.school.sba.util.ResponseStructure;

import jakarta.validation.Valid;

public interface UserService {

	ResponseEntity<ResponseStructure<UserResponse>> saveUser( UserRequest userRequest);

	ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId);

	ResponseEntity<ResponseStructure<UserResponse>> findUserById(int userId);

	ResponseEntity<ResponseStructure<UserResponse>> saveAdmin(UserRequest request);

}
