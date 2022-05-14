package com.capgemini.main.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.main.fixtures.DataFixtures;
import com.capgemini.main.model.User;
import com.capgemini.main.repository.UserRepository;
import com.capgemini.main.service.impl.UserServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class UserServiceTests {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userService;

	@Test
	void validate_GetUsers_HappyScenario() {
		when(userRepository.findAll()).thenReturn(DataFixtures.getUsers());
		List<User> users = userService.getAllUsers();
		Assertions.assertNotNull(users);
		Assertions.assertTrue(users.size() > 0);
		Assertions.assertNotNull(users.get(0));
		Assertions.assertEquals(2, users.size());
	}
	
	@Test
	void validate_GetUsers_NotExist() {
		when(userRepository.findAll()).thenReturn(null);
		List<User> users = userService.getAllUsers();
		Assertions.assertNull(users);
	}
	
	@Test
	void validate_GetUserById_HappyScenario() {
		when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(DataFixtures.getUser(1, "capgemini")));
		Optional<User> userOptional = userService.getUserById(1);
		Assertions.assertNotNull(userOptional);
		User user = userOptional.get();
		Assertions.assertNotNull(user.getId());
		Assertions.assertEquals(1, user.getId());
		Assertions.assertEquals("capgemini", user.getName());
		
	}
	
	@Test
	void validate_GetUserById_NotExist() {
		when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		Optional<User> userOptional = userService.getUserById(1);
		assertThat(userOptional).isEmpty();
		Assertions.assertFalse(userOptional.isPresent());
	}
}
