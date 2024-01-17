package com.school.sba.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.School;
import com.school.sba.entity.User;
import com.school.sba.enumuration.UserRole;
import com.school.sba.exception.InvalidUserException;
import com.school.sba.exception.SchoolAlreadyPresentForAdminException;
import com.school.sba.exception.UserNotFoundByIdException;
import com.school.sba.repositary.SchoolRepo;
import com.school.sba.repositary.UserRepositary;
import com.school.sba.requestdto.SchoolRequest;
import com.school.sba.responsedto.SchoolResponse;
import com.school.sba.service.SchoolService;
import com.school.sba.util.ResponseStructure;

@Service
public class SchoolServiceImpl implements SchoolService{
	
	@Autowired
	private SchoolRepo schoolRepo;
	
	@Autowired
	private UserRepositary userRepositary;
	
	@Autowired
	private ResponseStructure<SchoolResponse> responseStructure;
 
	
	private School mapToSchoolRequest(SchoolRequest request) {
		System.err.println(request.getAddress());
		return School.builder()
			   .schoolName(request.getSchoolName())
			   .contactNo(request.getContactNo())
			   .emailId(request.getEmailId())
			   .address(request.getAddress()).build();
				
	}
	
	private SchoolResponse mapToSchoolResponse(School school) {
		return SchoolResponse.builder()
				.schoolName(school.getSchoolName())
				.contactNo(school.getContactNo())
				.emailId(school.getEmailId())
				.address(school.getAddress())
				.schoolId(school.getSchoolId()).build();
	}

	@Override
	public ResponseEntity<ResponseStructure<SchoolResponse>> createSchool(SchoolRequest schoolRequest, int userId) {
		User admin=userRepositary.findById(userId).orElseThrow(()-> new UserNotFoundByIdException("User Id not exist"));
		if(admin.getUserRole()!=UserRole.ADMIN) {
			throw new InvalidUserException("only ADMIN can create school");
		}
		if(admin.getSchool()!=null) {
			throw new SchoolAlreadyPresentForAdminException("School already exists");
		}

		School school=schoolRepo.save(mapToSchoolRequest(schoolRequest));
		admin.setSchool(school);
		userRepositary.save(admin);
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("School created for ADMIN");
		responseStructure.setData(mapToSchoolResponse(school));
		return new ResponseEntity<ResponseStructure<SchoolResponse>>(responseStructure,HttpStatus.CREATED);
	}

}
