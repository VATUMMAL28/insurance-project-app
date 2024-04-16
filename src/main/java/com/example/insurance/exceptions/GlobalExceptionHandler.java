package com.example.insurance.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String,String> msgs=new HashMap<>();
		List<ObjectError> errs=ex.getBindingResult().getAllErrors();
		errs.forEach(e->{
				
				String field=((FieldError)e).getField();
				String msg=e.getDefaultMessage();
				msgs.put(field, msg);
	});
		
		return new ResponseEntity<Object>(msgs,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails>
		handleResourceException(ResourceNotFoundException ex, WebRequest r)
		{
			ErrorDetails details=new ErrorDetails();
			details.setDate(LocalDateTime.now());
			details.setMessage(ex.getMessage());
			details.setPath(r.getDescription(false));
			
		return new ResponseEntity<ErrorDetails>(details,HttpStatus.NOT_FOUND);
		}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails>
		handleOtherException(Exception ex, WebRequest r)
		{
			ErrorDetails details=new ErrorDetails();
			details.setDate(LocalDateTime.now());
			details.setMessage(ex.getMessage());
			details.setPath(r.getDescription(false));
			
		return new ResponseEntity<ErrorDetails>(details,HttpStatus.INTERNAL_SERVER_ERROR);
		}
}
