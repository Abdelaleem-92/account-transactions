package com.capgemini.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.main.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
