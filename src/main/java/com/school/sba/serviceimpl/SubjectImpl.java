package com.school.sba.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.Subject;
import com.school.sba.exception.AcademicProgramNotFoundById;
import com.school.sba.repositary.AcademicProgramRepo;
import com.school.sba.repositary.SubjectRepo;
import com.school.sba.requestdto.SubjectRequest;
import com.school.sba.responsedto.AcademicProgramResponse;
import com.school.sba.service.SubjectService;
import com.school.sba.util.ResponseStructure;

@Service
public class SubjectImpl implements SubjectService {

	@Autowired
	private SubjectRepo subjectRepo;

	@Autowired
	private AcademicProgramRepo academicProgramRepo;

	@Autowired
	private ResponseStructure<AcademicProgramResponse> structure;

	@Autowired
	private AcademicProgramServiceImpl academicProgramServiceImpl;

	@Override
	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> addSubject(SubjectRequest subjectRequest,int programId) {

		return academicProgramRepo.findById(programId).map(program ->{
			List<Subject> subjects=new ArrayList<Subject>();
			subjectRequest.getSubjectNames().forEach(name-> {

				Subject subject=subjectRepo.findBySubjectName(name).map(s->s).orElseGet(()->{
					Subject subject2=new Subject();
					subject2.setSubjectName(name);
					subjectRepo.save(subject2);
					return subject2;
				});
				subjects.add(subject);

			});
			program.setSubjects(subjects);
			academicProgramRepo.save(program);
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setMessage("Updated the Subject list to Academic Program");
			structure.setData(academicProgramServiceImpl. mapToAcademicProgramResponse(program));
			return new ResponseEntity<ResponseStructure<AcademicProgramResponse>>(structure,HttpStatus.CREATED);

		}).orElseThrow(()->new AcademicProgramNotFoundById("Academic program Not found for given id"));

	}

	@Override
	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> updateSubject(SubjectRequest subjectRequest,
	        int programId) {

	    return academicProgramRepo.findById(programId).map(program -> {
	        // Remove the existing subjects for the program
	        program.getSubjects().clear();
	        academicProgramRepo.save(program);

	       return addSubject(subjectRequest, programId);

	    }).orElseThrow(() -> new AcademicProgramNotFoundById("Academic program Not found for given id"));

	}

}
