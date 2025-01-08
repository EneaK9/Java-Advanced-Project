package com.polis.hospitalmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@SpringBootApplication
public class HospitalManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(HospitalManagementApplication.class, args);

	}

	@ControllerAdvice
	public class GlobalExceptionHandler {
		@ExceptionHandler(Exception.class)
		public ResponseEntity<String> handleExcpeption(Exception ex){
			return new ResponseEntity<>("An error ocurred:" +  ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
