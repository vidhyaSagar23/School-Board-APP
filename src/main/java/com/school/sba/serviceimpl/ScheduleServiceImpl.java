package com.school.sba.serviceimpl;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.Schedule;
import com.school.sba.entity.School;
import com.school.sba.exception.ScheduleIsPresentException;
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


}
