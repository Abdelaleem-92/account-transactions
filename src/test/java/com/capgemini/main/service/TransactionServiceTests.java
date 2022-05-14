package com.capgemini.main.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.main.fixtures.DataFixtures;
import com.capgemini.main.model.Transaction;
import com.capgemini.main.model.TransactionTypeEnum;
import com.capgemini.main.repository.TransactionRepository;
import com.capgemini.main.service.impl.TransactionServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class TransactionServiceTests {

	@Mock
	private TransactionRepository transactionRepository;

	@InjectMocks
	private TransactionServiceImpl transactionService;

	@Test
	void validate_AddAccount_HappyScenario_BalanceGreaterThanZero() {
		when(transactionRepository.save(Mockito.any()))
				.thenReturn(DataFixtures.getTransaction(1, TransactionTypeEnum.CREDIT, 100));
		Transaction transaction = transactionService.addTransaction(DataFixtures.getAccount(1, 100),
				TransactionTypeEnum.CREDIT, 100);
		assertNotNull(transaction);
		assertNotNull(transaction.getId());
		assertEquals(100, transaction.getTransactionAmount());
	}
}
