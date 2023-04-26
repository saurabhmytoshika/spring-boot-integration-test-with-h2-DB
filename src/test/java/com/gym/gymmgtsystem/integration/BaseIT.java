package com.gym.gymmgtsystem.integration;

import com.gymmgtsystem.GymMgtSystemApplication;
import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@Rollback
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@SpringBootTest(classes = {GymMgtSystemApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Tag("INTEGRATION")
public class BaseIT {

}
