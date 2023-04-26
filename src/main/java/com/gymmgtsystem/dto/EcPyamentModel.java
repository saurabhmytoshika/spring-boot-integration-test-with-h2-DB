package com.gymmgtsystem.dto;

import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Data
@Document(indexName = "paymentindex", type = "payment")
public class EcPyamentModel {
	
	private String userName;
	private String month;
	private int date;
	private double amount;
	private String description;

}
