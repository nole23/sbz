package com.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);


}
