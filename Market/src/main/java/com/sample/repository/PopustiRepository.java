package com.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.model.Account;
import com.sample.model.Popusti;

public interface PopustiRepository extends JpaRepository<Popusti, Long> {

	List<Popusti> findByAccount(Account account);

}
