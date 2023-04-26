package com.gymmgtsystem.dto;

import lombok.Data;

@Data
public class FeignError {
	
	private String code;
	private String links;
	private String title;
	private String message;

}
