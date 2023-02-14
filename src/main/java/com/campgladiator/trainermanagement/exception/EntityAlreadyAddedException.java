package com.campgladiator.trainermanagement.exception;

import org.springframework.http.HttpStatus;

public class EntityAlreadyAddedException extends TrainerManagementException{

	private static final long serialVersionUID = 1L;

	public EntityAlreadyAddedException(String message) {
		super(message, HttpStatus.CONFLICT);
	}
}
