package com.capgemini.main.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.capgemini.main.fixtures.DataFixtures;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class AuthControllerTests {

	@Autowired
	private MockMvc mvc;

	ObjectMapper mapper = new ObjectMapper();

	@Test
	void should_Return200_When_CorrectCredentials() throws Exception {
		String requestJson = mapper.writeValueAsString(DataFixtures.getLoginRequest_ValidCredentials());
		mvc.perform(MockMvcRequestBuilders.post("/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
				.content(requestJson)).andExpect((status().isOk()));
	}

	@Test
	void should_Return401_When_InvalidCredentials() throws Exception {
		String requestJson = mapper.writeValueAsString(DataFixtures.getLoginRequest_InValidCredentials());
		mvc.perform(MockMvcRequestBuilders.post("/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
				.content(requestJson)).andExpect((status().isUnauthorized()));
	}

	@Test
	void should_Return400_When_BadRequest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
				.content("{\"username\": \"capgemini\", \"password_Wrong\":\"WRONG_VALUE\"}"))
				.andExpect((status().isBadRequest()));
	}
}
