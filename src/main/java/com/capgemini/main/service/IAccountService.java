package com.capgemini.main.service;

import com.capgemini.main.model.Account;

public interface IAccountService {

	public Account addAccount(long userId, double initalCredit);
}
