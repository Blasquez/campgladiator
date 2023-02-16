package com.campgladiator.trainermanagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.campgladiator.trainermanagement.TrainerManagementApplication;
import com.campgladiator.trainermanagement.model.Trainer;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
  classes = TrainerManagementApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
  locations = "classpath:application-integrationtest.properties")
@TestMethodOrder(OrderAnnotation.class)
public class TrainerControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
    ObjectMapper objectMapper;
	
	@Test
    @Order(1)
    @DisplayName("Happy Path - The trainer was created successfully")
    void postTrainerSuccessfully() throws Exception {
		mockMvc.perform(post("/trainers")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic dGVzdDp0ZXN0")
                .content("{\"email\":\"diego_blasquez@teste.com\",\"phone\": \"1234567890\",\"first_name\" : \"Diego_Test\",\"last_name\" : \"Blasquez\"}"))
        .andExpect(status().isCreated())
        .andExpect(header().string("Location", "/trainers/trainer-id-000001"));
		
	 }
	
	@Test
    @Order(2)
    @DisplayName("409 - TreinerController.postTrainer(treiner) returns 409. Trainer has already added.")
    void postTrainer_thenEntityAlreadyAddedException() throws Exception {
		mockMvc.perform(post("/trainers")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Basic dGVzdDp0ZXN0")
                .content("{\"email\":\"diego_blasquez@teste.com\",\"phone\": \"1234567890\",\"first_name\" : \"Diego_Test\",\"last_name\" : \"Blasquez\"}"))
        .andExpect(status().isConflict());
	}
	
	@Test
    @Order(3)
    @DisplayName("Happy Path - Returns the trainer filtered by ID")
    void getTrainer() throws Exception{
		MvcResult trainer = mockMvc.perform(get("/trainers/trainer-id-000001").header("Authorization", "Basic dGVzdDp0ZXN0"))
        	.andExpect(status().isOk())
        	.andReturn();
		
		String content = trainer.getResponse().getContentAsString();
		assertEquals("{\"id\":\"trainer-id-000001\",\"email\":\"diego_blasquez@teste.com\",\"phone\":\"1234567890\",\"first_name\":\"Diego_Test\",\"last_name\":\"Blasquez\"}", content);
	}
	
	@Test
    @Order(4)
    @DisplayName("404 -TreinerController.getTrainer(treiner) returns 404. Trainer not found.")
    void getTrainerById_thenNotFoundExcepetion() throws Exception{
		mockMvc.perform(get("/trainers/trainer-id-000002").header("Authorization", "Basic dGVzdDp0ZXN0"))
	        	.andExpect(status().isNotFound());
	}
	
	@Test
    @Order(5)
    @DisplayName("400 - TreinerController.postTrainer(treiner) returns 400. Email is required.")
    void postTrainer_thenBadRequestException_emailRequired() throws Exception {
		mockMvc.perform(post("/trainers")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Basic dGVzdDp0ZXN0")
                .content("{\"phone\": \"1234567890\",\"first_name\" : \"Diego_Test\",\"last_name\" : \"Blasquez\"}"))
        .andExpect(status().isBadRequest());
	}
	
	@Test
    @Order(6)
    @DisplayName("400 - TreinerController.postTrainer(treiner) returns 400. Email is not valid.")
    void postTrainer_thenBadRequestException_emailNotValid() throws Exception {
		mockMvc.perform(post("/trainers")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Basic dGVzdDp0ZXN0")
                .content("{\"email\":\"diego_blasquez\",\"phone\": \"1234567890\",\"first_name\" : \"Diego_Test\",\"last_name\" : \"Blasquez\"}"))
        .andExpect(status().isBadRequest());
	}
	
	@Test
    @Order(7)
    @DisplayName("400 - TreinerController.postTrainer(treiner) returns 400. First Name is required.")
    void postTrainer_thenBadRequestException_firstNameRequired() throws Exception {
		mockMvc.perform(post("/trainers")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Basic dGVzdDp0ZXN0")
                .content("{\"email\":\"diego_blasquez\",\"phone\": \"1234567890\",\"last_name\" : \"Blasquez\"}"))
        .andExpect(status().isBadRequest());
	}
	
	@Test
    @Order(8)
    @DisplayName("400 - TreinerController.postTrainer(treiner) returns 400. Phone is not valid.")
    void postTrainer_thenBadRequestException_phoneNotValid() throws Exception {
		mockMvc.perform(post("/trainers")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Basic dGVzdDp0ZXN0")
                .content("{\"email\":\"diego_blasquez@teste.com\",\"phone\": \"1234567890hh\",\"first_name\" : \"Diego_Test\",\"last_name\" : \"Blasquez\"}"))
        .andExpect(status().isBadRequest());
	}
	
	@Test
    @Order(9)
    @DisplayName("POST Trainer Unauthorized")
    void postTrainerUnauthorized() throws Exception {
		mockMvc.perform(post("/trainers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"diego_blasquez@teste.com\",\"phone\": \"1234567890\",\"first_name\" : \"Diego_Test\",\"last_name\" : \"Blasquez\"}"))
        .andExpect(status().isUnauthorized());
		
	 }
	
	@Test
    @Order(10)
    @DisplayName("GET Trainer Unauthorizedr")
    void getTrainerUnauthorized() throws Exception{
		mockMvc.perform(get("/trainers/trainer-id-000001"))
        	.andExpect(status().isUnauthorized());
	}
}
