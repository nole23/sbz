package com.sample.dto;

import com.sample.model.CategoryUser;

public class CategoryUserDTO {

	private Long id;
	private String nameCategory;
	private SpendingLimitDTO spendingLimitDTO;
	private ProfileUserDTO profileUserDTO;

	public CategoryUserDTO() {
	}

	public CategoryUserDTO(Long id, String nameCategory,
			SpendingLimitDTO spendingLimitDTO, ProfileUserDTO profileUserDTO) {
		super();
		this.id = id;
		this.nameCategory = nameCategory;
		this.spendingLimitDTO = spendingLimitDTO;
		this.profileUserDTO = profileUserDTO;
	}

	public CategoryUserDTO(CategoryUser categoryUser) {
		super();
		this.id = categoryUser.getId();
		this.nameCategory = categoryUser.getNameCategory();
		if (categoryUser.getSpendingLimit() != null)
			this.spendingLimitDTO = new SpendingLimitDTO(
					categoryUser.getSpendingLimit());
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

	public SpendingLimitDTO getSpendingLimitDTO() {
		return spendingLimitDTO;
	}

	public void setSpendingLimitDTO(SpendingLimitDTO spendingLimitDTO) {
		this.spendingLimitDTO = spendingLimitDTO;
	}

	public ProfileUserDTO getProfileUserDTO() {
		return profileUserDTO;
	}

	public void setProfileUserDTO(ProfileUserDTO profileUserDTO) {
		this.profileUserDTO = profileUserDTO;
	}

}
