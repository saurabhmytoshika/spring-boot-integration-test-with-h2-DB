package com.gymmgtsystem.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="trainer")
public class TrainerEntity extends Auditable<String> {

	    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="trainer_id", unique = true, nullable = false, length = 10)
 	    private Integer trainerId;	    
	    @Column(name = "trainer_name", nullable = false)
		private String trainerName;
	    @Column(name = "email_id", nullable = false)
		private String emailId;
	    @Column(name = "phone_number", nullable = false)
		private String phoneNumber;
	    @Column(name = "date_of_birth", nullable = false)
		private String dateOfBirth ;
	    @Embedded
		private Address address;
		
}
