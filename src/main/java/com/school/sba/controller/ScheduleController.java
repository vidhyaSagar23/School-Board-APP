package com.school.sba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.sba.requestdto.ScheduleRequest;
import com.school.sba.responsedto.ScheduleResponse;
import com.school.sba.service.ScheduleService;
import com.school.sba.util.ResponseStructure;

@RestController
public class ScheduleController {
	@Autowired
	private ScheduleService scheduleService;
	
	@PostMapping("/schools/{schoolId}/schedules")
	public ResponseEntity<ResponseStructure<ScheduleResponse>> createSchedule(@PathVariable int schoolId,@RequestBody ScheduleRequest request){
		return scheduleService.createSchedule(request, schoolId);
	}
	
	@GetMapping("/schools/{scheduleId}/schedules")
	public ResponseEntity<ResponseStructure<ScheduleResponse>> findSchedule(@PathVariable int scheduleId){
		return scheduleService.findSchedule(scheduleId);
	}
	
	@PutMapping("/schedules/{scheduleId}")
	public ResponseEntity<ResponseStructure<ScheduleResponse>> updateSchedule(@PathVariable int scheduleId,@RequestBody ScheduleRequest request){
		System.out.println("update schedule");
		return scheduleService.updateSchedule(scheduleId,request);
	}
	
}
