package com.campgladiator.trainermanagement.database;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campgladiator.trainermanagement.model.Trainer;

@Transactional
@Repository
public interface TrainerRepository extends JpaRepository<Trainer, String>{

}
