package com.sample.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Pokazuje nam koliko mozemo da smanjimo racun za datog korisnika
 * ako je korisnik postao zlatni korisnik ili tako dalje
 * @author X
 *
 */

@Entity
public class SpendingLimit {

	@Id
	@GeneratedValue
	private Long id;
	private int minLimint;
	private int maxLimit;
	private int discountPercent;

	@OneToMany(mappedBy = "spendingLimit", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<CategoryUser> categoryUser = new HashSet<CategoryUser>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getMinLimint() {
		return minLimint;
	}

	public void setMinLimint(int minLimint) {
		this.minLimint = minLimint;
	}

	public int getMaxLimit() {
		return maxLimit;
	}

	public void setMaxLimit(int maxLimit) {
		this.maxLimit = maxLimit;
	}

	public int getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}

	public Set<CategoryUser> getCategoryUser() {
		return categoryUser;
	}

	public void setCategoryUser(Set<CategoryUser> categoryUser) {
		this.categoryUser = categoryUser;
	}

}
