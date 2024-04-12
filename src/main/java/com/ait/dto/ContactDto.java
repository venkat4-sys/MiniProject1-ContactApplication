package com.ait.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ContactDto {

	private Integer contactId;
	
	@NotEmpty(message = "name should not be null or empty")
	private String name;
	
	@NotEmpty(message = "email should not be null or empty")
	private String email;
	
	private Long contactNumber;

	
}
