package com.sample.dto;

import com.sample.model.ListActionCategory;

public class ListActionCategoryDTO {

	private Long id;
	private CategoryArticleDTO categoryArticleDTO;
	private ActionEventDTO actionEventDTO;

	public ListActionCategoryDTO() {
	}

	public ListActionCategoryDTO(Long id,
			CategoryArticleDTO categoryArticleDTO, ActionEventDTO actionEventDTO) {
		super();
		this.id = id;
		this.categoryArticleDTO = categoryArticleDTO;
		this.actionEventDTO = actionEventDTO;
	}

	public ListActionCategoryDTO(ListActionCategory listAction) {
		this.id = listAction.getId();
		if (listAction.getActionEvent() != null)
			this.actionEventDTO = new ActionEventDTO(
					listAction.getActionEvent());
		;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CategoryArticleDTO getCategoryArticleDTO() {
		return categoryArticleDTO;
	}

	public void setCategoryArticleDTO(CategoryArticleDTO categoryArticleDTO) {
		this.categoryArticleDTO = categoryArticleDTO;
	}

	public ActionEventDTO getActionEventDTO() {
		return actionEventDTO;
	}

	public void setActionEventDTO(ActionEventDTO actionEventDTO) {
		this.actionEventDTO = actionEventDTO;
	}

}
