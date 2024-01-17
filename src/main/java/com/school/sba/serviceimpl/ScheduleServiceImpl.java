package com.school.sba.serviceimpl;

import java.time.Duration;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.Schedule;
import com.school.sba.entity.School;
import com.school.sba.exception.ScheduleIsPresentException;
import com.school.sba.exception.ScheduleNotFoundById;
import com.school.sba.exception.SchoolNotFoundException;
import com.school.sba.repositary.ScheduleRepo;
import com.school.sba.repositary.SchoolRepo;
import com.school.sba.requestdto.ScheduleRequest;
import com.school.sba.responsedto.ScheduleResponse;
import com.school.sba.service.ScheduleService;
import com.school.sba.util.ResponseStructure;

@Service
public class ScheduleServiceImpl implements ScheduleService{


	@Autowired
	private ScheduleRepo scheduleRepo;

	@Autowired
	private SchoolRepo schoolRepo;

	@Autowired
	private ResponseStructure<ScheduleResponse> structure;

	private Schedule mapToScheduleRequest(ScheduleRequest request) {
		return Schedule.builder()
				.opensAt(request.getOpensAt())
				.closesAt(request.getClosesAt())
				.classHourLength(Duration.ofMinutes(request.getClassHourLengthInMins()))
				.lunchTime(request.getLunchTime())
				.lunchLength(Duration.ofMinutes(request.getLunchLengthInMins()))
				.breakTime(request.getBreakTime())
				.breakLength(Duration.ofMinutes(request.getBreakLengthInMins()))
				.classHoursPerDay(request.getClassHoursPerDay())
				.build();
	}

	private ScheduleResponse mapToScheduleResponse(Schedule schedule) {
        return ScheduleResponse.builder()
                .scheduleId(schedule.getScheduleId())
                .opensAt(schedule.getOpensAt())
                .closesAt(schedule.getClosesAt())
                .classHoursPerDay(schedule.getClassHoursPerDay())
                .classHourLengthInMins((int) schedule.getClassHourLength().toMinutes())
                .breakTime(schedule.getBreakTime())
                .breakLengthInMins((int) schedule.getBreakLength().toMinutes())
                .lunchTime(schedule.getLunchTime())
                .lunchLengthInMins((int) schedule.getLunchLength().toMinutes())
                .build();
    }


	@Override
	public ResponseEntity<ResponseStructure<ScheduleResponse>> createSchedule(ScheduleRequest request, int schoolId) {
		School school=schoolRepo.findById(schoolId).orElseThrow(()-> new SchoolNotFoundException("Can't find any school in the given ID"));
		if(school.getSchedule()==null) {
			Schedule schedule=scheduleRepo.save(mapToScheduleRequest(request));
			school.setSchedule(schedule);
			schoolRepo.save(school);
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setMessage("New Schedule is created");
			structure.setData(mapToScheduleResponse(schedule));
		}
		else {
			throw new ScheduleIsPresentException("Schedule is already present for this school");
		}
		return new ResponseEntity<ResponseStructure<ScheduleResponse>>(structure,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<ScheduleResponse>> findSchedule(int scheduleId) {
		Schedule schedule=scheduleRepo.findById(scheduleId).orElseThrow(()-> new ScheduleNotFoundById("Can't find any schedule in the given ID"));
		
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setMessage("Schedule Found");
		structure.setData(mapToScheduleResponse(schedule));
		return new ResponseEntity<ResponseStructure<ScheduleResponse>>(structure,HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<ResponseStructure<ScheduleResponse>> updateSchedule(int scheduleId, ScheduleRequest request) {
		Schedule schedule=scheduleRepo.findById(scheduleId).orElseThrow(()-> new ScheduleNotFoundById("Can't find any schedule in the given ID"));
	    if (Objects.nonNull(request.getOpensAt())) {
	        schedule.setOpensAt(request.getOpensAt());
	    }
	    if (Objects.nonNull(request.getClosesAt())) {
	        schedule.setClosesAt(request.getClosesAt());
	    }
	    if (Objects.nonNull(request.getBreakTime())) {
	        schedule.setBreakTime(request.getBreakTime());
	    }
	    if (Objects.nonNull(request.getBreakLengthInMins())) {
	        schedule.setBreakLength(Duration.ofMinutes(request.getBreakLengthInMins()));
	    }
	    if (Objects.nonNull(request.getClassHoursPerDay())) {
	        schedule.setClassHoursPerDay(request.getClassHoursPerDay());
	    }
	    if (Objects.nonNull(request.getClassHourLengthInMins())) {
	        schedule.setClassHourLength(Duration.ofMinutes(request.getClassHourLengthInMins()));
	    } else {
	        schedule.setClassHourLength(null);
	    }
	    if (Objects.nonNull(request.getLunchTime())) {
	        schedule.setLunchTime(request.getLunchTime());
	    }
	    if (Objects.nonNull(request.getLunchLengthInMins())) {
	        schedule.setLunchLength(Duration.ofMinutes(request.getLunchLengthInMins()));
	    } else {
	        schedule.setLunchLength(null);
	    }
//		if(request.getOpensAt() != null) {
//			schedule.setOpensAt(request.getOpensAt());
//		}
//		if(request.getClosesAt() != null) {
//			schedule.setClosesAt(request.getClosesAt());
//		}
//		if(request.getBreakTime() != null) {
//			schedule.setBreakTime(request.getBreakTime());
//		}
//		if(request.getBreakLengthInMins()!= null) {
//			schedule.setBreakLength(Duration.ofMinutes(request.getBreakLengthInMins()));
//		}
//		if(request.getClassHoursPerDay() != null) {
//			schedule.setClassHoursPerDay(request.getClassHoursPerDay());
//		}
//		if(request.getClassHourLengthInMins()!= null) {
//			schedule.setClassHourLength(Duration.ofMinutes(request.getClassHourLengthInMins()));
//		}
//		if(request.getLunchTime()!=null) {
//			schedule.setLunchTime(request.getLunchTime());
//		}
//		if(request.getLunchLengthInMins()!= null) {
//			schedule.setLunchLength(Duration.ofMinutes(request.getLunchLengthInMins()));
//		}
		scheduleRepo.save(schedule);
		structure.setStatus(HttpStatus.ACCEPTED.value());
		structure.setMessage("Schedule updated");
		structure.setData(mapToScheduleResponse(schedule));
		return new ResponseEntity<ResponseStructure<ScheduleResponse>>(structure,HttpStatus.ACCEPTED);
	}
}
