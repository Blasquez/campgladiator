package com.campgladiator.trainermanagement.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.campgladiator.trainermanagement.exception.EntityAlreadyAddedException;
import com.campgladiator.trainermanagement.exception.NotFoundException;
import com.campgladiator.trainermanagement.model.Trainer;
import com.campgladiator.trainermanagement.service.TrainerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/trainers")
@Tag(name = "trainer", description = "Trainer Management")
@SecurityRequirement(name = "basicAuth")
public class TrainerController {
	
	@Autowired
	private TrainerService service;
	
	@Operation(summary = "Create a new trainer", tags = "trainer")
	@ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Created"),
	                       @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content(schema = @Schema(implementation = ResponseStatusException.class))}),
	                       @ApiResponse(responseCode = "409", description = "Conflict", content = {@Content(schema = @Schema(implementation = ResponseStatusException.class))}),
	                       @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(schema = @Schema(implementation = ResponseStatusException.class))})})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Void> postTrainer(@Valid @RequestBody final Trainer trainer) throws EntityAlreadyAddedException, URISyntaxException{
		String id = service.save(trainer);
		URI uri = new URI("/trainers/" + id);
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping(value = "{trainerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Trainer> getTrainer(@PathVariable(name = "trainerId",required = true) final String id) throws NotFoundException{
		return ResponseEntity.ok(service.getTrainerById(id));
	}

}
