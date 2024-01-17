//package com.school.sba.entity;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToOne;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//public class School {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int schoolId;
//	private String schoolName;
//	private long contactNo;
//	private String email;
//	private String address;
//	
//	@OneToOne
//	private Schedule schedule;
//	
//}
