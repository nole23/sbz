package com.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.model.OrderArticle;

public interface OrderArticleRepository extends JpaRepository<OrderArticle, Long> {

}
