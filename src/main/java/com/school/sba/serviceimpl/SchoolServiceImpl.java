//package com.school.sba.serviceimpl;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.school.sba.entity.School;
//import com.school.sba.exception.SchoolNotFoundException;
//import com.school.sba.repositary.SchoolRepositary;
//import com.school.sba.service.SchoolService;
//
//public class SchoolServiceImpl implements SchoolService{
//
//	@Autowired
//	private SchoolRepositary schoolRepositary;
//	
//	@Override
//	public School saveSchool(School school ) {
//		return schoolRepositary.save(school);
//	}
//
//	@Override
//	public School getSchoolById(int id) {
//		
//		Optional<School> school = schoolRepositary.findById(id);
//		School school2 = school.get();
//		if(school2==null) throw new SchoolNotFoundException("SCHOOL IS NOT IN DATABASE");
//		return school2;
//	}
//
//	@Override
//	public School updateSchool(School school) {
//		return schoolRepositary.save(school);
//	}
//
//	@Override
//	public void deleteSchoolById(int id) {
//		schoolRepositary.delete(schoolRepositary.findById(id).get());
//		
//	}
//
//}
