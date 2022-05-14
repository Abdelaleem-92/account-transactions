package com.capgemini.main.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.main.model.User;
import com.capgemini.main.repository.UserRepository;
import com.capgemini.main.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> getUserById(long userId) {
		return userRepository.findById(userId);
	}

	@Override
	public void deleteUserById(long userId) {
		userRepository.deleteById(userId);
	}

}
