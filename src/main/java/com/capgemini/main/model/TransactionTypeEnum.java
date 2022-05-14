package com.capgemini.main.model;

public enum TransactionTypeEnum {

	DEBIT("DEBIT"), CREDIT("CREDIT");

	private String transactionType;

	TransactionTypeEnum(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionType() {
		return transactionType;
	}

}
