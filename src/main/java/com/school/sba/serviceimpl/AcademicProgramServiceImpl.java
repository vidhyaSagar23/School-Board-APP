package com.school.sba.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.AcademicProgram;
import com.school.sba.entity.School;
import com.school.sba.entity.Subject;
import com.school.sba.entity.User;
import com.school.sba.enumuration.UserRole;
import com.school.sba.exception.AcademicProgramNotFoundById;
import com.school.sba.exception.AdminCannotBeAssignedToAcademicException;
import com.school.sba.exception.InvalidUserException;
import com.school.sba.exception.SchoolNotFoundException;
import com.school.sba.exception.UserAlreadyMappedException;
import com.school.sba.exception.UserNotFoundByIdException;
import com.school.sba.repositary.AcademicProgramRepo;
import com.school.sba.repositary.SchoolRepo;
import com.school.sba.repositary.UserRepositary;
import com.school.sba.requestdto.AcademicProgramRequest;
import com.school.sba.responsedto.AcademicProgramResponse;
import com.school.sba.service.AcademicProgramService;
import com.school.sba.util.ResponseStructure;

@Service
public class AcademicProgramServiceImpl implements AcademicProgramService{

	@Autowired
	private ResponseStructure<AcademicProgramResponse> responseStructure;

	@Autowired
	private SchoolRepo schoolRepo;

	@Autowired
	private AcademicProgramRepo academicProgramRepo;

	@Autowired
	private UserRepositary userRepositary;

	private AcademicProgram mapToAcademicProgramRequest(AcademicProgramRequest request) {
		return AcademicProgram.builder()
				.beginsAt(request.getBeginsAt())
				.endsAt(request.getEndsAt())
				.programName(request.getProgramName())
				.programType(request.getProgramType())
				.build();
	}

	public AcademicProgramResponse mapToAcademicProgramResponse(AcademicProgram academicProgram) {
		List<String> subjects=new ArrayList<>();
		for(Subject subject:academicProgram.getSubjects()) {
			subjects.add(subject.getSubjectName());
		}
		return AcademicProgramResponse.builder()
				.beginsAt(academicProgram.getBeginsAt())
				.endsAt(academicProgram.getEndsAt())
				.programId(academicProgram.getProgramId())
				.programName(academicProgram.getProgramName())
				.programType(academicProgram.getProgramType())
				.subjects(subjects)
				.build();
	}

	@Override
	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> createAcademicProgram(
			AcademicProgramRequest request, int schoolId) {	
		School school=schoolRepo.findById(schoolId).orElseThrow(()->new SchoolNotFoundException("School not found in the given Id"));
		AcademicProgram academicProgram =mapToAcademicProgramRequest(request);
		academicProgram.setSchool(school);
		academicProgramRepo.save(academicProgram);
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Academic program created for school");
		responseStructure.setData(mapToAcademicProgramResponse(academicProgram));
		return new ResponseEntity<ResponseStructure<AcademicProgramResponse>>(responseStructure,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<AcademicProgramResponse>>> findAllAcademicPrograms(int schoolId) {
		return schoolRepo.findById(schoolId).map(school->{
			List<AcademicProgram> list=school.getAcademicPrograms();
			ResponseStructure<List<AcademicProgramResponse>> rs=new ResponseStructure<>();
			List<AcademicProgramResponse> l=new ArrayList<>();

			for(AcademicProgram obj:list) {
				l.add(mapToAcademicProgramResponse(obj));
			}
			rs.setStatus(HttpStatus.FOUND.value());
			rs.setMessage("Academic program's found");
			rs.setData(l);
			return new ResponseEntity<ResponseStructure<List<AcademicProgramResponse>>>(rs,HttpStatus.FOUND);
		}).orElseThrow(()->new SchoolNotFoundException("School not found in the given Id"));
	}

	@Override
	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> addUsersToProgram(int programId,
			int userId) {
		
		return userRepositary.findById(userId).map(user->{
			return academicProgramRepo.findById(programId).map(program->{
				if(user.getUserRole() != UserRole.ADMIN) {
					user.getAcademicProgram().add(program);
					userRepositary.save(user);
					program.getUsers().add(user);
					academicProgramRepo.save(program);
					responseStructure.setStatus(HttpStatus.CREATED.value());
					responseStructure.setMessage("user added to Academic program");
					responseStructure.setData(mapToAcademicProgramResponse(program));
					return new ResponseEntity<ResponseStructure<AcademicProgramResponse>>(responseStructure,HttpStatus.CREATED);
				}
				throw new AdminCannotBeAssignedToAcademicException("Admin Cannot be added to Academic Program");
			}).orElseThrow(()-> new AcademicProgramNotFoundById("Academic Program Not found in the given Id"));
		}).orElseThrow(()-> new UserNotFoundByIdException("User Not found in the given Id"));
	}
}
