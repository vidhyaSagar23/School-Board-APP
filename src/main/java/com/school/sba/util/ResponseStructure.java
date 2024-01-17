package com.school.sba.util;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Controller
@Component
public class ResponseStructure<T> {
	
	private int status;
	private String message;
	private T data;
	

}
