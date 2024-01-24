package com.school.sba.repositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.school.sba.entity.User;
import com.school.sba.enumuration.UserRole;

public interface UserRepositary extends JpaRepository<User, Integer>{

	boolean existsByUserRole(UserRole userRole);

	Optional<User> findByUserName(String username);

}
