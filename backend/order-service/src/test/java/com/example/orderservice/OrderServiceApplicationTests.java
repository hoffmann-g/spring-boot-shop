package com.example.orderservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class OrderServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Container
	private static MySQLContainer<?> mysql = new MySQLContainer<>(DockerImageName.parse("mysql:5.7.34"));

	@DynamicPropertySource
	static void setPropertiets(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", () -> mysql.getJdbcUrl());
	}

	@Test()
    public void connectToDatabase() {
		try{
			mysql.start();
			mysql.close();
		} catch (Exception e){
			e.printStackTrace();
		} 
        
    }

}
