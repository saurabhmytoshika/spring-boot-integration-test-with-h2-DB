package com.gymmgtsystem.entity;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Address {

	private String address1;
	private String address2;
	private String city;
	private String state;
	private String pinCode;
}
