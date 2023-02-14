package com.campgladiator.trainermanagement.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TrainerManagementException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private final String message;
	
	private final HttpStatus status;

}
