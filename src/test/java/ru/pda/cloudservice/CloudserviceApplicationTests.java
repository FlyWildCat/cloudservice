package ru.pda.cloudservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

//@Testcontainers
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudserviceApplicationTests {

//	@Autowired
//	private TestRestTemplate restTemplate;
//
//	@Container
//	private final GenericContainer<?> cloudsrvice = new GenericContainer<>("cloudservice:1.0")
//			.withExposedPorts(8080)
//			;

	@Test
	void contextLoads() {
//		Integer port = cloudsrvice.getMappedPort(8080);

//		ResponseEntity<?> entity = restTemplate.getForEntity("http://localhost:" + port + "/login", String.class);
	}

}
