package com.etr.toll.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.etr.toll.exception.TollException;

@RestControllerAdvice
public class TollExceptionController {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(TollException.class)
	public Map<String,String> handleException(TollException ex){
		Map<String,String> errorMap = new HashMap<>();
		errorMap.put(ex.type.getMessage(), ex.getMessage());
		return errorMap;
	}
	
}
