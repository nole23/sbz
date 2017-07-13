package com.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.model.SpendingLimit;

public interface SpendingLimitRepository extends JpaRepository<SpendingLimit, Long> {

}
