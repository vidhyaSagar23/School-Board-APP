package com.school.sba.service;

import org.springframework.http.ResponseEntity;

import com.school.sba.requestdto.ScheduleRequest;
import com.school.sba.responsedto.ScheduleResponse;
import com.school.sba.util.ResponseStructure;

public interface ScheduleService {

	ResponseEntity<ResponseStructure<ScheduleResponse>> createSchedule(ScheduleRequest request, int schoolId);

	

}
