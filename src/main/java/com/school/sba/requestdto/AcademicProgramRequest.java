package com.school.sba.requestdto;

import java.time.LocalTime;

import org.springframework.stereotype.Component;

import com.school.sba.enumuration.ProgramType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class AcademicProgramRequest {
	private String programName;
	private LocalTime beginsAt;
	private LocalTime endsAt;
	private ProgramType programType;
}
