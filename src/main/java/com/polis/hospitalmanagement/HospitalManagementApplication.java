package com.polis.hospitalmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * This is the main class of the Hospital Management System.
 * It serves as the entry point of the Spring Boot application.
 */
@SpringBootApplication
public class HospitalManagementApplication {
	/**
	 * The main method that starts the application.
	 * @param args Command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(HospitalManagementApplication.class, args);

	}

	/**
	 * Global Exception Handler class to manage all unhandled exceptions
	 * and return meaningful error responses to the client.
	 */
	@ControllerAdvice
	public static class GlobalExceptionHandler {
		/**
		 * Catches any general Exception and returns an HTTP 500 (Internal Server Error) response.
		 * @param ex The exception that was thrown.
		 * @return A ResponseEntity with an error message and HTTP status.
		 */
		@ExceptionHandler(Exception.class)
		public ResponseEntity<String> handleExcpeption(Exception ex){
			return new ResponseEntity<>("An error ocurred:" +  ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
