package com.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.model.RModel;

public interface RRepository extends JpaRepository<RModel, Long> {

}
