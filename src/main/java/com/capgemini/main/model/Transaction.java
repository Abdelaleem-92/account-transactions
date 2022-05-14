package com.capgemini.main.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "transactions")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne()
	@JoinColumn(name = "account_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Account accountId;

	@Column(name = "transaction_type")
	@Enumerated(EnumType.STRING)
	private TransactionTypeEnum transactionType;

	@Column(name = "transaction_amount", nullable = false)
	private double transactionAmount;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "transaction_datetime", nullable = false)
	private Date transactionDatetime = new Date();
	
	public Transaction(Long id, TransactionTypeEnum transactionType, double transactionAmount) {
		super();
		this.id = id;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
	}

	public Transaction() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Account getAccountId() {
		return accountId;
	}

	public void setAccountId(Account accountId) {
		this.accountId = accountId;
	}

	public TransactionTypeEnum getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionTypeEnum transactionType) {
		this.transactionType = transactionType;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Date getTransactionDatetime() {
		return transactionDatetime;
	}

	public void setTransactionDatetime(Date transactionDatetime) {
		this.transactionDatetime = transactionDatetime;
	}

}
