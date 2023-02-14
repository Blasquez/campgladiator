package com.campgladiator.trainermanagement.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends TrainerManagementException{

	private static final long serialVersionUID = 1L;

	public NotFoundException(String message) {
		super(message, HttpStatus.NOT_FOUND);
	}

}
