package com.sample.dto;

import java.util.HashSet;
import java.util.Set;

import com.sample.model.SpendingLimit;

public class SpendingLimitDTO {

	private Long id;
	private int minLimint;
	private int maxLimit;
	private int discountPercent;
	private Set<CategoryUserDTO> categoryUserDTO = new HashSet<CategoryUserDTO>();

	public SpendingLimitDTO() {
	}

	public SpendingLimitDTO(Long id, int minLimint, int maxLimit,
			int discountPercent, Set<CategoryUserDTO> categoryUserDTO) {
		super();
		this.id = id;
		this.minLimint = minLimint;
		this.maxLimit = maxLimit;
		this.discountPercent = discountPercent;
		this.categoryUserDTO = categoryUserDTO;
	}

	public SpendingLimitDTO(SpendingLimit spendingLimit) {

		this.id = spendingLimit.getId();
		this.minLimint = spendingLimit.getMinLimint();
		this.maxLimit = spendingLimit.getMaxLimit();
		this.discountPercent = spendingLimit.getDiscountPercent();
		
	}

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

	public Set<CategoryUserDTO> getCategoryUserDTO() {
		return categoryUserDTO;
	}

	public void setCategoryUserDTO(Set<CategoryUserDTO> categoryUserDTO) {
		this.categoryUserDTO = categoryUserDTO;
	}

}
