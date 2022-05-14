package com.capgemini.main.service;

import java.util.List;
import java.util.Optional;

import com.capgemini.main.model.User;

public interface IUserService {

	public List<User> getAllUsers();

	public Optional<User> getUserById(long userId);
	
	public void deleteUserById(long userId);
}
