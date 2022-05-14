package com.capgemini.main.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.main.payload.JwtResponse;
import com.capgemini.main.payload.LoginRequest;
import com.capgemini.main.security.service.UserDetailsImpl;
import com.capgemini.main.security.util.JwtTokenUtil;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api")
public class AuthController {

	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenUtil jwtUtils;

	@PostMapping("auth/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//String encodedPassword = passwordEncoder.encode(loginRequest.getPassword());
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		log.info("Authinticated Successfully.");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtToken = jwtUtils.generateToken(authentication.getName());
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwtToken, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 roles));
	}
}
