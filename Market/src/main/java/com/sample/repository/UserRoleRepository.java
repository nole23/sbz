package com.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.model.User;
import com.sample.model.UserRola;

public interface UserRoleRepository extends JpaRepository<UserRola, Long> {

	UserRola findByUser(User user);

}
