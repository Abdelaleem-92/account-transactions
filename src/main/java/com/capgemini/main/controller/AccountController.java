package com.capgemini.main.controller;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.main.model.Account;
import com.capgemini.main.payload.UserInfo;
import com.capgemini.main.service.IAccountService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api")
public class AccountController {

	private static final Logger log = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	IAccountService accountService;

	@PostMapping("/accounts")
	@PreAuthorize("hasRole('ROLE_ALLOW_ADD')")
	public ResponseEntity<Account> addNewAccount(@NotNull @RequestBody UserInfo userInfo) {
		Account createdAccount = accountService.addAccount(userInfo.getCustomerId(), userInfo.getInitalCredit());
		log.info("Created Account with id <{}> ", createdAccount.getId());
		return ResponseEntity.ok().body(createdAccount);
	}
}
