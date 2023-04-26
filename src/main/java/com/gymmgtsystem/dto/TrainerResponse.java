package com.gymmgtsystem.dto;

import lombok.Data;

@Data
public class TrainerResponse {

	private Integer trainerId;	    
	private String trainerName;
	private String emailId;
	private String phoneNumber;
	private String dateOfBirth ;
	private AddressResponse address;

}
