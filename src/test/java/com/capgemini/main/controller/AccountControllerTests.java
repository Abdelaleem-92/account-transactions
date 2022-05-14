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
import com.capgemini.main.security.util.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class AccountControllerTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	JwtTokenUtil jwtUtils;

	ObjectMapper mapper = new ObjectMapper();

	@Test // with valid JWT token -> status is 200
	void should_AllowAccessWithValidTokenTo_CreateAccount() throws Exception {
		String requestJson = mapper.writeValueAsString(DataFixtures.getUserInfo(1, 200));
		String jwtToken = jwtUtils.generateToken("capgemini_1");
		mvc.perform(MockMvcRequestBuilders.post("/api/accounts").contentType(MediaType.APPLICATION_JSON)
				.content(requestJson).header("Authorization", "Bearer " + jwtToken)).andExpect(status().isOk());
	}

	@Test
	void should_NotAllowAccessTo_CreateAccount_BadCredentials() throws Exception {
		String requestJson = mapper.writeValueAsString(DataFixtures.getUserInfo(1, 200));
		mvc.perform(MockMvcRequestBuilders.post("/api/accounts").contentType(MediaType.APPLICATION_JSON)
				.content(requestJson)).andExpect(status().isUnauthorized());
	}

	@Test
	void should_NotAllowAccessTo_CreateAccount_BadRequest() throws Exception {
		String jwtToken = jwtUtils.generateToken("capgemini_1");
		mvc.perform(MockMvcRequestBuilders.post("/api/accounts").header("Authorization", "Bearer " + jwtToken))
				.andExpect(status().isBadRequest());
	}

}
