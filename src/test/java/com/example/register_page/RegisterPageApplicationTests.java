package com.example.register_page;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class RegisterPageApplicationTests {

	@Test
	void contextLoads() {
		// Test will pass if the application context loads successfully
	}

}
