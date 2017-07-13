package com.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.model.Cart;
import com.sample.model.User;

public interface CartRepository extends JpaRepository<Cart, Long> {

	List<Cart> findByUser(User user);

}
