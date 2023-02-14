package com.campgladiator.trainermanagement.exception.handler;

import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.campgladiator.trainermanagement.exception.EntityAlreadyAddedException;
import com.campgladiator.trainermanagement.exception.NotFoundException;
import com.campgladiator.trainermanagement.exception.TrainerManagementException;
import com.campgladiator.trainermanagement.model.ErrorResponse;

@RestControllerAdvice
public class TrainerManagementExceptionHandler {

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
		Set<String> validations = new HashSet<>();
		exception.getAllErrors().stream().forEach(error -> validations.add(error.getDefaultMessage()));
		return ErrorResponse.builder().validations(validations)
				                      .message("Required fields")
									  .title(HttpStatus.BAD_REQUEST.name())
				                      .statusCode(HttpStatus.BAD_REQUEST.value())
				                      .build();
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(EntityAlreadyAddedException.class)
	public ErrorResponse handleDeviceAlreadyAddedException(final TrainerManagementException exception) {
		return ErrorResponse.builder().message(exception.getMessage())
				                      .title(exception.getStatus().name())
				                      .statusCode(exception.getStatus().value())
				                      .build();
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ErrorResponse handleDeviceNotFoundException(final TrainerManagementException exception) {
		return ErrorResponse.builder().message(exception.getMessage())
				                      .title(exception.getStatus().name())
				                      .statusCode(exception.getStatus().value())
				                      .build();
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(URISyntaxException.class)
	public ErrorResponse handleURISyntaxException(final URISyntaxException exception) {
		return ErrorResponse.builder().message(exception.getMessage())
				                      .title(HttpStatus.INTERNAL_SERVER_ERROR.name())
				                      .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				                      .build();
	}
}
