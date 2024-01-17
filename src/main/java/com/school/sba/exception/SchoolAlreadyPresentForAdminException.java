package com.school.sba.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SchoolAlreadyPresentForAdminException extends RuntimeException {
	private String message;
}
