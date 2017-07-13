package com.sample.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Popusti {

	@Id
	@GeneratedValue
	private Long id;
	private String tipPopusta;
	private int procenat;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Account account;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private AccountFinis accountFinis;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipPopusta() {
		return tipPopusta;
	}

	public void setTipPopusta(String tipPopusta) {
		this.tipPopusta = tipPopusta;
	}

	public int getProcenat() {
		return procenat;
	}

	public void setProcenat(int procenat) {
		this.procenat = procenat;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public AccountFinis getAccountFinis() {
		return accountFinis;
	}

	public void setAccountFinis(AccountFinis accountFinis) {
		this.accountFinis = accountFinis;
	}

}
