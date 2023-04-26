package com.gym.gymmgtsystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;

import com.gymmgtsystem.GymMgtSystemApplication;

@SpringBootTest(classes = {GymMgtSystemApplication.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class GymMgtSystemApplicationTests {
	
	@Test
	void contextLoads() { }

}                                                         
