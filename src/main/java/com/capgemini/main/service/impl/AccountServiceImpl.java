package com.capgemini.main.service.impl;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.main.exception.NotFoundException;
import com.capgemini.main.model.Account;
import com.capgemini.main.model.Transaction;
import com.capgemini.main.model.TransactionTypeEnum;
import com.capgemini.main.model.User;
import com.capgemini.main.repository.AccountRepository;
import com.capgemini.main.service.IAccountService;
import com.capgemini.main.service.ITransactionService;
import com.capgemini.main.service.IUserService;

@Service
public class AccountServiceImpl implements IAccountService {

	private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ITransactionService transactionService;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public Account addAccount(long userId, double initalCredit) {
		Optional<User> userOptional = userService.getUserById(userId);
		if(userOptional.isPresent()) {
			Account accountNew = new Account();
			accountNew.setUserId(userOptional.get());
			accountNew.setBalance(initalCredit);
			accountNew.setCreatedDate(new Date());
			Account createdAccount = accountRepository.save(accountNew);
			if(initalCredit > 0) {
				addTransaction(createdAccount, initalCredit);
			}
			
			return createdAccount;
		}else {
			log.error("NOT EXIST USER with id <{}>.", userId);
			throw new NotFoundException("NOT EXIST USER with id "+userId, 404);
		}
		
	}
	
	private void addTransaction(Account createdAccount, double initalCredit) {
		Transaction addedTransaction = transactionService.addTransaction(createdAccount, TransactionTypeEnum.CREDIT, initalCredit);
		createdAccount.getAccountTransactions().add(addedTransaction);
		
	}

}
