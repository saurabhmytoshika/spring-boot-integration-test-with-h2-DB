package com.gymmgtsystem.dto;

import lombok.Getter;

@Getter
public enum Status {
	SUCCESS(1, "SUCCESS"), 
	FAILURE(0, "FAILURE"), 
	ACTIVE(2, "ACTIVE"),
    INACTIVE(3,"INACTIVE");
	
	private final Integer value;
    private final String displayName;
    
    Status(Integer value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

}
