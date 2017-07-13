package com.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.model.CategoryUser;

public interface CategoryUserRepository extends JpaRepository<CategoryUser, Long> {

}
