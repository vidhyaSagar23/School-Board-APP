package com.school.sba.responsedto;

import java.time.LocalTime;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ScheduleResponse {
	private int scheduleId;
	private LocalTime opensAt;
	private LocalTime closesAt;
	private int classHoursPerDay;
	private int classHourLengthInMins;
	private LocalTime breakTime;
	private int breakLengthInMins;
	private LocalTime lunchTime;
	private int lunchLengthInMins;
}
