package com.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.model.CategoryArticle;
import com.sample.model.ListActionCategory;

public interface ListActionCategoryRepository extends JpaRepository<ListActionCategory, Long> {

	ListActionCategory findByCategoryArticle(CategoryArticle categoryArticle);

}
