package com.school.sba.util;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class ExceptionResponse<T> {
	private int status;
	private String message;
	private String rootCause;
}
