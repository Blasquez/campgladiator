package com.campgladiator.trainermanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.campgladiator.trainermanagement.database.TrainerRepository;
import com.campgladiator.trainermanagement.exception.EntityAlreadyAddedException;
import com.campgladiator.trainermanagement.exception.NotFoundException;
import com.campgladiator.trainermanagement.model.Trainer;
import com.campgladiator.trainermanagement.service.TrainerService;

@Service
public class TrainerServiceImpl implements TrainerService {

	@Autowired
	private TrainerRepository repository;
	
	@Override
	public String save(Trainer trainer) throws EntityAlreadyAddedException{
		try {
			trainer = repository.save(trainer);
		} catch (DataIntegrityViolationException e) {
			throw new EntityAlreadyAddedException("There is a trainer with email: " + trainer.getEmail() + ".");
		}
		return trainer.getId();
	}

	@Override
	public Trainer getTrainerById(String id) throws NotFoundException{
		return repository.findById(id).orElseThrow(() -> new NotFoundException("There isn't a trainer for id " + id));
	}

}
