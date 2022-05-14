package com.capgemini.main.service;

import com.capgemini.main.model.Account;
import com.capgemini.main.model.Transaction;
import com.capgemini.main.model.TransactionTypeEnum;

public interface ITransactionService {

	public Transaction addTransaction(Account account, TransactionTypeEnum transactionType,  double transactionAmount);
}
