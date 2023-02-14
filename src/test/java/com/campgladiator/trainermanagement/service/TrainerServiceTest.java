package com.campgladiator.trainermanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import com.campgladiator.trainermanagement.database.TrainerRepository;
import com.campgladiator.trainermanagement.exception.EntityAlreadyAddedException;
import com.campgladiator.trainermanagement.exception.NotFoundException;
import com.campgladiator.trainermanagement.model.Trainer;
import com.campgladiator.trainermanagement.service.impl.TrainerServiceImpl;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class TrainerServiceTest {

	@Mock
	private TrainerRepository repository;
	
	@InjectMocks
	private TrainerServiceImpl service;
	
	private Trainer sampleTrainer;
	
	@BeforeEach
    void setup(){
		sampleTrainer = Trainer.builder().id("trainer-id-000001")
				                   .email("trainer@campgladiator.com")
				                   .phone("5125125120")
				                   .firstName("Fearless")
				                   .lastName("Contender")
				                   .build();
    }
	
	@Test
    @Order(1)
    @DisplayName("Happy Path - The trainer was created successfully")
    void saveTrainerSuccessfully() {
    	 when(repository.save(ArgumentMatchers.any(Trainer.class))).thenReturn(sampleTrainer);
         
    	 String id = service.save(new Trainer(null, "diego@test.com", "1234567890", "Diego", "Blasquez"));
    	 
    	 assertEquals("trainer-id-000001", id);
    }
	
	@Test
    @Order(2)
    @DisplayName("409 - TreinerServiceImpl.save(treiner) returns 409. Trainer has already added.")
    void saveTrainer_thenEntityAlreadyAddedException() {
    	 when(repository.save(ArgumentMatchers.any(Trainer.class))).thenThrow(DataIntegrityViolationException.class);
    	 EntityAlreadyAddedException exception = Assertions.assertThrows(EntityAlreadyAddedException.class, () -> {
    		 	service.save(new Trainer(null, "diego@test.com", "1234567890", "Diego", "Blasquez"));
    	 	});
    	 Assertions.assertEquals("There is a trainer with email: diego@test.com.", exception.getMessage());
		 Assertions.assertEquals(409, exception.getStatus().value());
    }
	
	@Test
    @Order(3)
    @DisplayName("Happy Path - Returns the trainer filtered by ID")
    void getTrainerById() {
    	 when(repository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(sampleTrainer));
         Trainer trainer = service.getTrainerById("trainer-id-000001");
    	 assertEquals("trainer-id-000001", trainer.getId());
    	 assertEquals("trainer@campgladiator.com", trainer.getEmail());
    }
    
    @Test
    @Order(4)
    @DisplayName("404 -TreinerServiceImpl.getTrainerById(string) returns 404. Trainer not found.")
    void getTrainerById_thenNotFoundExcepetion() {
    	 when(repository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.empty());
    	 NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> { 
    		 	service.getTrainerById("trainer-id-000002"); 
    		 });
        	 
    	 Assertions.assertEquals("There isn't a trainer for id trainer-id-000002", exception.getMessage());
		 Assertions.assertEquals(404, exception.getStatus().value());
    }
}
