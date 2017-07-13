package com.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sample.model.Article;
import com.sample.model.PodCategory;

public interface ArticleRepository extends JpaRepository<Article, Long> {

	List<Article> findByPodCategory(PodCategory podCategory);

	@Query("SELECT f FROM Article as f WHERE (f.nameArticle LIKE %:nameArticle%)")
	List<Article> search(@Param("nameArticle") String nameArticle);
	
	
}
