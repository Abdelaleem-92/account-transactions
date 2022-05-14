package com.capgemini.main.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username")
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 120)
	@JsonIgnore
	private String password;
	
	@NotBlank
	@Size(max = 20)
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	@JsonIgnore
	private Set<Role> roles = new HashSet<>();
	
	@Transient
	private double totalBalance;
	
	public User() {
		super();
	}

	public User(Long id, String username, String name) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userId")
	private Set<Account> userAccounts = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Account> getUserAccounts() {
		return userAccounts;
	}

	public void setUserAccounts(Set<Account> userAccounts) {
		this.userAccounts = userAccounts;
	}

	public double getTotalBalance() {
		return getUserAccounts().stream().mapToDouble(Account::getBalance).sum();
	}

	public void setTotalBalance(double totoalBalance) {
		this.totalBalance = totoalBalance;
	}
	
	
}
