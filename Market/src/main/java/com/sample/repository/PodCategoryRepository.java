package com.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.model.CategoryArticle;
import com.sample.model.PodCategory;

public interface PodCategoryRepository extends JpaRepository<PodCategory, Long> {

	List<PodCategory> findByCategoryArticle(CategoryArticle categoryArticle);

}
