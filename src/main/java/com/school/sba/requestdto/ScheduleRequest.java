package com.school.sba.requestdto;

import java.time.LocalTime;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class ScheduleRequest {
	private LocalTime opensAt;
	private LocalTime closesAt;
	private int classHoursPerDay;
	private int classHourLengthInMins;
	private LocalTime breakTime;
	private int breakLengthInMins;
	private LocalTime lunchTime;
	private int lunchLengthInMins;
}
