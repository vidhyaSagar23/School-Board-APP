package com.school.sba.responsedto;

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
public class AcademicProgramResponse {
	private int programId;
	private String programName;
	private LocalTime beginsAt;
	private LocalTime endsAt;
	private ProgramType programType;
}
