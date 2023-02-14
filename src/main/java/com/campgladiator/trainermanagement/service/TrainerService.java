package com.campgladiator.trainermanagement.service;

import com.campgladiator.trainermanagement.exception.EntityAlreadyAddedException;
import com.campgladiator.trainermanagement.exception.NotFoundException;
import com.campgladiator.trainermanagement.model.Trainer;

public interface TrainerService {

	String save( Trainer trainer) throws EntityAlreadyAddedException;
	
	Trainer getTrainerById(final String id) throws NotFoundException;
}
