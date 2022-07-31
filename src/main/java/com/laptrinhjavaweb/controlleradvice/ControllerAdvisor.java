package com.laptrinhjavaweb.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.laptrinhjavaweb.dto.response.ResponseDTO;
import com.laptrinhjavaweb.exception.FieldNotFoundException;

@ControllerAdvice // chuc nang xu ly ngoai le
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(FieldNotFoundException.class) // Trả ra HTTPstatuscode Đúng với status của nó
	public ResponseEntity<Object> handleCityNotFoundException(
	       FieldNotFoundException ex, WebRequest request) {
			
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setData(null);
		responseDTO.setDetail(ex.getMessage());
		
	    return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/*@ExceptionHandler(Exception.class) // Trả ra HTTPstatuscode Đúng với status của nó
	public ResponseEntity<Object> handleCityNotFoundException(
	        FieldNotFoundException ex, WebRequest request) {
			
		ResponseABC responseABC = new ResponseABC();
		responseABC.setData(null);
		responseABC.setError(ex.getMessage());
		
	    return new ResponseEntity<>(responseABC, HttpStatus.INTERNAL_SERVER_ERROR);
	}*/

}
