package com.gymmgtsystem.dto;

import lombok.Data;

@Data
public class AddressRequest {

	private String address1;
	private String address2;
	private String city;
	private String state;
	private String pinCode;
}
