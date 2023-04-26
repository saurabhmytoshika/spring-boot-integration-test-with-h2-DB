package com.gymmgtsystem.dto;

import lombok.Data;

@Data
public class TrainerRequestDto {

	private String trainerId;
	private String trainerName;
	private String emailId;
	private String phoneNumber;
	private String dateOfBirth;
	private AddressRequest address;
	
}
