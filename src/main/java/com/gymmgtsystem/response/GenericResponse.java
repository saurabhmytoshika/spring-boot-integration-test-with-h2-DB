package com.gymmgtsystem.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gymmgtsystem.dto.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(value = Include.NON_EMPTY)
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse {

	/** The message. */
	String message;
	
	/** The status. */
	Status status = Status.SUCCESS;
	
	String error;
	
	String token;
	
	Integer records;
	
}
