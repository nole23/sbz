package com.sample.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/*
 * Mesto gde se nalaze gotovi racuni
 */

@Entity
public class ListItemsAccountFinis {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private ItemsAccountFinis itemsAccountFinis;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private AccountFinis accountFinis;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ItemsAccountFinis getItemsAccountFinis() {
		return itemsAccountFinis;
	}

	public void setItemsAccountFinis(ItemsAccountFinis itemsAccountFinis) {
		this.itemsAccountFinis = itemsAccountFinis;
	}

	public AccountFinis getAccountFinis() {
		return accountFinis;
	}

	public void setAccountFinis(AccountFinis accountFinis) {
		this.accountFinis = accountFinis;
	}

}
