package com.school.sba.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.sba.entity.User;

public interface UserRepositary extends JpaRepository<User, Integer>{

}
