package com.sample.dto;

import java.util.HashSet;
import java.util.Set;

import com.sample.model.CategoryArticle;
import com.sample.model.ListActionCategory;

public class CategoryArticleDTO {

	private Long id;
	private String uniqueCode;// jedinstvena sifra
	private String nameCategory;
	private boolean sirokePotrosnje;
	private int maxDiscount;
	private Set<ArticleDTO> articleDTO = new HashSet<ArticleDTO>();
	private Set<ListActionCategoryDTO> listActionCategoryDTO = new HashSet<ListActionCategoryDTO>();

	public CategoryArticleDTO() {
	}

	public CategoryArticleDTO(Long id, String uniqueCode, String nameCategory, boolean sirokePotrosnje, int maxDiscount,
			PodCategoryDTO podCategoryDTO, Set<ArticleDTO> articleDTO,
			Set<ListActionCategoryDTO> listActionCategoryDTO) {
		super();
		this.id = id;
		this.uniqueCode = uniqueCode;
		this.nameCategory = nameCategory;
		this.sirokePotrosnje = sirokePotrosnje;
		this.maxDiscount = maxDiscount;
		this.articleDTO = articleDTO;
		this.listActionCategoryDTO = listActionCategoryDTO;
	}

	public CategoryArticleDTO(CategoryArticle categoryArticle) {
		this.id = categoryArticle.getId();
		this.uniqueCode = categoryArticle.getUniqueCode();
		this.nameCategory = categoryArticle.getNameCategory();
		this.sirokePotrosnje = categoryArticle.isSirokePotrosnje();
		this.maxDiscount = categoryArticle.getMaxDiscount();
		if (categoryArticle.getListActionCategory() != null) {
			this.listActionCategoryDTO = new HashSet<ListActionCategoryDTO>();
			for (ListActionCategory lac : categoryArticle.getListActionCategory()) {
				this.listActionCategoryDTO.add(new ListActionCategoryDTO(lac));
			}
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameCategory() {
		return nameCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}

	public Set<ArticleDTO> getArticleDTO() {
		return articleDTO;
	}

	public void setArticleDTO(Set<ArticleDTO> articleDTO) {
		this.articleDTO = articleDTO;
	}

	public Set<ListActionCategoryDTO> getListActionCategoryDTO() {
		return listActionCategoryDTO;
	}

	public void setListActionCategoryDTO(Set<ListActionCategoryDTO> listActionCategoryDTO) {
		this.listActionCategoryDTO = listActionCategoryDTO;
	}

	public String getUniqueCode() {
		return uniqueCode;
	}

	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}

	public boolean isSirokePotrosnje() {
		return sirokePotrosnje;
	}

	public void setSirokePotrosnje(boolean sirokePotrosnje) {
		this.sirokePotrosnje = sirokePotrosnje;
	}

	public int getMaxDiscount() {
		return maxDiscount;
	}

	public void setMaxDiscount(int maxDiscount) {
		this.maxDiscount = maxDiscount;
	}

}
