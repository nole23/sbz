package com.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.model.Account;
import com.sample.model.User;

public interface AccountReposotory extends JpaRepository<Account, Long> {

	List<Account> findByUser(User user);

}
