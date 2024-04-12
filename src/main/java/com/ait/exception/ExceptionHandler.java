package com.ait.exception;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.ait.dto.ContactError;

@RestControllerAdvice
public class ExceptionHandler {
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,WebRequest webrequest){
	
		Map<String, String> map=new HashMap<>();
		
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		
		for(FieldError field:fieldErrors) {
			
			map.put(field.getField(), field.getDefaultMessage());
			
		}
		
		return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		
		
	}
	@org.springframework.web.bind.annotation.ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<?> handleIdNotFoundException(IdNotFoundException ex,WebRequest webrequest){
	
		ContactError errors=new ContactError();
		
		errors.setMessage(ex.getMessage());
		
		errors.setLocalDate(LocalDate.now());
		
		errors.setInformation(webrequest.getDescription(false));
		
		
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
		
		
	}
	

}
