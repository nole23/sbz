package com.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.model.Account;
import com.sample.model.ItemsAccount;
import com.sample.model.ListItemsAccount;

public interface ListItemsAccountRepository extends JpaRepository<ListItemsAccount, Long> {

	List<ListItemsAccount> findByAccount(Account account);

	ListItemsAccount findByItemsAccount(ItemsAccount itemsAccount);

}
