package com.capgemini.main.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.main.exception.NotFoundException;
import com.capgemini.main.model.User;
import com.capgemini.main.service.IUserService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	IUserService userService;

	@GetMapping("users")
	public ResponseEntity<List<User>> allCities() {
		List<User> users = userService.getAllUsers();
		return ResponseEntity.ok().body(users);
	}

	@GetMapping("users/{userId}")
	public ResponseEntity<User> getUserById(@NotNull @PathVariable long userId) {
		Optional<User> userOptional = userService.getUserById(userId);
		if(!userOptional.isPresent()) {
			log.error("NOT EXIST USER with id <{}>.", userId);
			throw new NotFoundException("NOT EXIST USER with id "+userId, 404);
		}
		return ResponseEntity.ok().body(userOptional.get());
	}
	
	@DeleteMapping("users/{userId}")
	@PreAuthorize("hasRole('ROLE_ALLOW_DELETE')")
	public ResponseEntity<?> deleteUser(@NonNull @PathVariable long userId) {
		userService.deleteUserById(userId);
		return ResponseEntity.noContent().build();
		
	}
}
