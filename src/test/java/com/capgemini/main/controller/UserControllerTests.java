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

import com.capgemini.main.security.util.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class UserControllerTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	JwtTokenUtil jwtUtils;

	ObjectMapper mapper = new ObjectMapper();

	@Test // with valid JWT token -> status is 200
	void should_AllowAccessWithValidTokenTo_AllUsers() throws Exception {
		String jwtToken = jwtUtils.generateToken("capgemini_1");
		mvc.perform(MockMvcRequestBuilders.get("/api/users").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + jwtToken)).andExpect(status().isOk());
	}

	@Test
	void should_NotAllowAccessTo_AllUsers__BadCredentials() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/users").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnauthorized());
	}

	@Test // with valid JWT token -> status is 200
	void shouldAllowAccessWithValidTokenTo_GetUser() throws Exception {
		String jwtToken = jwtUtils.generateToken("capgemini_1");
		mvc.perform(MockMvcRequestBuilders.get("/api/users/2").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + jwtToken)).andExpect(status().isOk());
	}

	@Test
	void should_NotAllowAccessTo_GetUser__BadCredentials() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/users/2").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnauthorized());
	}
}
