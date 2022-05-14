package com.capgemini.main.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.main.model.Account;
import com.capgemini.main.model.Transaction;
import com.capgemini.main.model.TransactionTypeEnum;
import com.capgemini.main.repository.TransactionRepository;
import com.capgemini.main.service.ITransactionService;

@Service
public class TransactionServiceImpl implements ITransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public Transaction addTransaction(Account account, TransactionTypeEnum transactionType, double transactionAmount) {
		Transaction transactionNew = new Transaction();
		transactionNew.setAccountId(account);
		transactionNew.setTransactionAmount(transactionAmount);
		transactionNew.setTransactionType(transactionType);
		transactionNew.setTransactionDatetime(new Date());
		return transactionRepository.save(transactionNew);
	}

}
