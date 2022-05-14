package com.capgemini.main.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.main.exception.NotFoundException;
import com.capgemini.main.fixtures.DataFixtures;
import com.capgemini.main.model.Account;
import com.capgemini.main.repository.AccountRepository;
import com.capgemini.main.service.impl.AccountServiceImpl;
import com.capgemini.main.service.impl.TransactionServiceImpl;
import com.capgemini.main.service.impl.UserServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class AccountServiceTests {
	
	@Mock
	private UserServiceImpl userService;
	
	@Mock
	private TransactionServiceImpl transactionService;
	
	@Mock
	private AccountRepository accountRepository;
	
	@InjectMocks
	private AccountServiceImpl accountService;


	@Test
	void validate_AddAccount_HappyScenario_BalanceGreaterThanZero() {
		when(userService.getUserById(Mockito.anyLong())).thenReturn(Optional.of(DataFixtures.getUser(1l, "capgemini")));
		when(accountRepository.save(Mockito.any())).thenReturn(DataFixtures.getAccount(1l, 200));
		Account acc = accountService.addAccount(1l, 200);
		assertNotNull(acc);
		assertNotNull(acc.getId());
		assertEquals(200, acc.getBalance());
	}
	
	@Test
	void validate_AddAccount_HappyScenario_BalanceEqualsZero() {
		when(userService.getUserById(Mockito.anyLong())).thenReturn(Optional.of(DataFixtures.getUser(1l, "capgemini")));
		when(accountRepository.save(Mockito.any())).thenReturn(DataFixtures.getAccount(1l, 0));
		Account acc = accountService.addAccount(1l, 0);
		assertNotNull(acc);
		assertNotNull(acc.getId());
		assertEquals(0, acc.getBalance());
	}
	
	@Test
	void validate_AddAccount_NotExistCustomerId() {
		when(userService.getUserById(Mockito.anyLong())).thenReturn(Optional.empty());
		when(accountRepository.save(Mockito.any())).thenReturn(DataFixtures.getAccount(1l, 0));
		NotFoundException exception = assertThrows(NotFoundException.class, () -> accountService.addAccount(1l, 0));
		assertEquals("NOT EXIST USER with id 1", exception.getMessage());
	}
}
