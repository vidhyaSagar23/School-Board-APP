package com.school.sba.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminCannotBeAssignedToAcademicException extends RuntimeException {
	private String message;
}
