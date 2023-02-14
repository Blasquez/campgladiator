package com.campgladiator.trainermanagement.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"email"}))
public class Trainer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name = "trainer_seq", strategy = "com.campgladiator.trainermanagement.database.CustomIdGenerator")
	@GeneratedValue(generator = "trainer_seq")
	private String id;
	
	@NotBlank(message = "Email is required.")
	@NotNull(message = "Email is required.")
	@Email(message = "It is not a valid email.")
	private String email;
	
	@Pattern(regexp = "[0-9]{10}", message = "The phone number should have 10 digits.")
	private String phone;
	
	@NotNull(message = "First Name is required.")
	@NotBlank(message = "First Name is required.")
	@JsonProperty(value = "first_name")
	private String firstName;
	
	@JsonProperty(value = "last_name")
	private String lastName;
	

}
