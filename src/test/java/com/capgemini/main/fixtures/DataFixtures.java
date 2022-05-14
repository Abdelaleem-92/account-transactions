package com.capgemini.main.fixtures;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.capgemini.main.model.Account;
import com.capgemini.main.model.Transaction;
import com.capgemini.main.model.TransactionTypeEnum;
import com.capgemini.main.model.User;
import com.capgemini.main.payload.LoginRequest;
import com.capgemini.main.payload.UserInfo;

public class DataFixtures {

	public static LoginRequest getLoginRequest_ValidCredentials() {
		LoginRequest req = new LoginRequest();
		req.setUsername("capgemini_1");
		req.setPassword("capgemini");
		return req;
	}

	public static LoginRequest getLoginRequest_InValidCredentials() {
		LoginRequest req = new LoginRequest();
		req.setUsername("capgemini");
		req.setPassword("Wrong_Value");
		return req;
	}
	
	public static UserInfo getUserInfo(long userId, double initalCredit) {
		UserInfo user = new UserInfo();
		user.setCustomerId(userId);
		user.setInitalCredit(initalCredit);
		return user;
	}
	
	public static List<User> getUsers(){
		List<User> users = new ArrayList<>();
		User user0 = new User(1l, "capgemini", "capgemini");
		User user1 = new User(2l, "capgemini2", "capgemini2");
		users.add(user0);
		users.add(user1);
		return users;
	}
	
	public static User getUser(long id, String name){
		return new User(id, name, name);
	}
	
	public static Account getAccount(long id, double balance){
		return  new Account(id, new Date(), balance);
	}
	
	public static Transaction getTransaction(long id, TransactionTypeEnum transactionType, double transactionAmount){
		return  new Transaction(id, transactionType, transactionAmount);
	}
}
