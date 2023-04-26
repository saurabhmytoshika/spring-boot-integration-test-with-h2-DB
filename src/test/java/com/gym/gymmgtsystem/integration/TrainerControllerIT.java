package com.gym.gymmgtsystem.integration;

import com.gymmgtsystem.entity.TrainerEntity;
import com.gymmgtsystem.repository.TrainerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TrainerControllerIT extends BaseIT {

//	private static final String TRAINER_ADD_REQUEST = "src/test/resources/add_trainer_request.json";

	@Autowired
    private TrainerRepository repository;

    @Autowired
    private MockMvc mockMvc;
    
//    private static final ObjectMapper mapper = new ObjectMapper();
    
//    private static TrainerRequestDto studentAddRequest() throws IOException {
//    	TrainerRequestDto req = mapper.readValue(new File(TRAINER_ADD_REQUEST), TrainerRequestDto.class);
//         return req;
//    }
    
	@Test
	@SqlGroup({ @Sql(value = "classpath:sql/flush/reset.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
			@Sql(value = "classpath:sql/init/trainer-data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD) })
	void should_create_trainer() throws Exception {
		
		// Arrange
		final File jsonFile = new ClassPathResource("models/add_trainer_request.json").getFile();
		final String addTrainerReq = Files.readString(jsonFile.toPath());

		// Act
		ResultActions response = this.mockMvc
				.perform(post("/api/trainer")
						.contentType(MediaType.APPLICATION_JSON)
						.content(addTrainerReq));

		// Assert
		response.andDo(print()).andExpect(status().isCreated()).andExpect(jsonPath("$.data", is(true)));

		assertThat(this.repository.findAll()).hasSize(6);
	} 
	
	@Test
	@SqlGroup({ @Sql(value = "classpath:sql/flush/reset.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
			@Sql(value = "classpath:sql/init/trainer-data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD) })
	void should_update_trainer() throws Exception {
		
		// Arrange
		final File jsonFile = new ClassPathResource("models/add_trainer_request.json").getFile();
		final String updateTrainerReq = Files.readString(jsonFile.toPath());

		// Act
		ResultActions response = this.mockMvc
				.perform(put("/api/trainer/{trainerId}", 5)
						.contentType(MediaType.APPLICATION_JSON)
						.content(updateTrainerReq));

		// Assert
		response.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.data", is(true)));

		List<TrainerEntity> data = this.repository.findAll();
		assertThat(data).hasSize(5);
		assertEquals("trainer_name", data.get(4).getTrainerName());
		assertEquals("20-01-1992", data.get(4).getDateOfBirth());
	}
	
	@Test
	@SqlGroup({ @Sql(value = "classpath:sql/flush/reset.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
			@Sql(value = "classpath:sql/init/trainer-data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD) })
	void should_fetch_trainer_by_id() throws Exception {
		
		// Act
		ResultActions response = this.mockMvc.perform(get("/api/trainer/{trainerId}", 1));

		// Assert
		response
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data.trainerId").value(1))
		.andExpect(jsonPath("$.data.trainerName").value("trainer_name1"))
		.andExpect(jsonPath("$.data.emailId").value("trainer1@gmail.com"))
		.andExpect(jsonPath("$.data.phoneNumber").value("1234567890"))
		.andExpect(jsonPath("$.data.dateOfBirth").value("01-01-1990"));
	}
	
	@Test
	@SqlGroup({ @Sql(value = "classpath:sql/flush/reset.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
			@Sql(value = "classpath:sql/init/trainer-data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD) })
	void should_fetch_all_the_trainers() throws Exception {

		// Act
		ResultActions response = this.mockMvc.perform(get("/api/trainer"));

		// Assert
		response
		.andDo(print())
		.andExpect(status().isOk())
       // .andExpect(jsonPath("$.data", hasSize(5)))
		.andExpect(jsonPath("$.data[0].trainerId").value(1))
		.andExpect(jsonPath("$.data[1].trainerId").value(2))
		.andExpect(jsonPath("$.data[2].trainerId").value(3))
		.andExpect(jsonPath("$.data[3].trainerId").value(4))
		.andExpect(jsonPath("$.data[4].trainerId").value(5))
		.andExpect(jsonPath("$.data[0].trainerName").value("trainer_name1"))
		.andExpect(jsonPath("$.data[0].emailId").value("trainer1@gmail.com"))
		.andExpect(jsonPath("$.data[0].phoneNumber").value("1234567890"))
		.andExpect(jsonPath("$.data[0].dateOfBirth").value("01-01-1990"));
	}
    
//    private TrainerRequestDto getAddTrainerRequest() {
//    	AddressRequest address = new AddressRequest();
//    	address.setAddress1("address1");
//    	address.setAddress2("address2");
//    	address.setCity("city");
//    	address.setPinCode("232111");
//    	address.setState("state");
//    	
//    	TrainerRequestDto dto = new TrainerRequestDto();
//    	dto.setTrainerName("Trainer_name");
//    	dto.setPhoneNumber("9876543211");
//    	dto.setEmailId("abc@gmail.com");
//    	dto.setDateOfBirth("1999-12-23");
//    	dto.setAddress(address);
//    	
//		return dto;
//    	
//    }

}
