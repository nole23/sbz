package com.sample.dto;

import java.util.HashSet;
import java.util.Set;

import com.sample.model.Article;
import com.sample.model.PodCategory;

public class PodCategoryDTO {

	private Long id;
	private String namePodCateogry;
	private CategoryArticleDTO categoryArticleDTO;
	private Set<Article> article = new HashSet<Article>();

	public PodCategoryDTO() {
	}

	public PodCategoryDTO(Long id, String namePodCateogry, CategoryArticleDTO categoryArticleDTO) {
		super();
		this.id = id;
		this.namePodCateogry = namePodCateogry;
		this.categoryArticleDTO = categoryArticleDTO;
	}

	public PodCategoryDTO(PodCategory podCategory) {
		this.id = podCategory.getId();
		this.namePodCateogry = podCategory.getNamePodCateogry();
		if (podCategory.getCategoryArticle() != null)
			this.categoryArticleDTO = new CategoryArticleDTO(podCategory.getCategoryArticle());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNamePodCateogry() {
		return namePodCateogry;
	}

	public void setNamePodCateogry(String namePodCateogry) {
		this.namePodCateogry = namePodCateogry;
	}

	public CategoryArticleDTO getCategoryArticleDTO() {
		return categoryArticleDTO;
	}

	public void setCategoryArticleDTO(CategoryArticleDTO categoryArticleDTO) {
		this.categoryArticleDTO = categoryArticleDTO;
	}

	public Set<Article> getArticle() {
		return article;
	}

	public void setArticle(Set<Article> article) {
		this.article = article;
	}

}
