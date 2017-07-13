package com.sample.dto;

import com.sample.model.ListItemsAccount;

public class ListItemsAccountDTO {

	private Long id;
	private ItemsAccountDTO itemsAccountDTO;
	private AccountDTO accountDTO;

	public ListItemsAccountDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ListItemsAccountDTO(Long id, ItemsAccountDTO itemsAccountDTO, AccountDTO accountDTO) {
		super();
		this.id = id;
		this.itemsAccountDTO = itemsAccountDTO;
		this.accountDTO = accountDTO;
	}

	public ListItemsAccountDTO(ListItemsAccount itemsAccount) {
		this.id = itemsAccount.getId();
		if (itemsAccount.getItemsAccount() != null)
			this.itemsAccountDTO = new ItemsAccountDTO(itemsAccount.getItemsAccount());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ItemsAccountDTO getItemsAccountDTO() {
		return itemsAccountDTO;
	}

	public void setItemsAccountDTO(ItemsAccountDTO itemsAccountDTO) {
		this.itemsAccountDTO = itemsAccountDTO;
	}

	public AccountDTO getAccountDTO() {
		return accountDTO;
	}

	public void setAccountDTO(AccountDTO accountDTO) {
		this.accountDTO = accountDTO;
	}

}
