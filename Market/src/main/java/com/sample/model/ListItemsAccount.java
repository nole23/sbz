package com.sample.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Ovaj model treba da poveze racun(account) sa itemsAccount(stavkom racuna)
 * 
 * @author X
 *
 */

@Entity
public class ListItemsAccount {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private ItemsAccount itemsAccount;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Account account;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ItemsAccount getItemsAccount() {
		return itemsAccount;
	}

	public void setItemsAccount(ItemsAccount itemsAccount) {
		this.itemsAccount = itemsAccount;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
