package com.school.sba.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.sba.entity.School;

public interface SchoolRepo extends JpaRepository<School, Integer>{

}
