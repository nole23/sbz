package com.sample.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class CategoryUser {

	@Id
	@GeneratedValue
	private Long id;
	private String nameCategory;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private SpendingLimit spendingLimit;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "categoryUser", cascade = CascadeType.ALL)
	private ProfileUser profileUser;

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

	public ProfileUser getProfileUser() {
		return profileUser;
	}

	public void setProfileUser(ProfileUser profileUser) {
		this.profileUser = profileUser;
	}

	public SpendingLimit getSpendingLimit() {
		return spendingLimit;
	}

	public void setSpendingLimit(SpendingLimit spendingLimit) {
		this.spendingLimit = spendingLimit;
	}

}
