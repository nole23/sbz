package com.sample.dto;

import com.sample.model.ProfileUser;

public class ProfileUserDTO {

	private Long id;
	private String country;
	private String city;
	private String street;
	private String numberStreet;
	private int rewardPoints;
	private CategoryUserDTO categoryUserDTO;
	private UserDTO userDTO;

	public ProfileUserDTO() {
	}

	public ProfileUserDTO(Long id, String country, String city, String street,
			String numberStreet, int rewardPoints,
			CategoryUserDTO categoryUserDTO, UserDTO userDTO) {
		super();
		this.id = id;
		this.country = country;
		this.city = city;
		this.street = street;
		this.numberStreet = numberStreet;
		this.rewardPoints = rewardPoints;
		this.categoryUserDTO = categoryUserDTO;
		this.userDTO = userDTO;
	}

	public ProfileUserDTO(ProfileUser profileUser) {
		super();
		this.id = profileUser.getId();
		this.country = profileUser.getCountry();
		this.city = profileUser.getCity();
		this.street = profileUser.getStreet();
		this.numberStreet = profileUser.getNumberStreet();
		this.rewardPoints = profileUser.getRewardPoints();
		if (profileUser.getCategoryUser() != null)
			this.categoryUserDTO = new CategoryUserDTO(
					profileUser.getCategoryUser());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumberStreet() {
		return numberStreet;
	}

	public void setNumberStreet(String numberStreet) {
		this.numberStreet = numberStreet;
	}

	public int getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	public CategoryUserDTO getCategoryUserDTO() {
		return categoryUserDTO;
	}

	public void setCategoryUserDTO(CategoryUserDTO categoryUserDTO) {
		this.categoryUserDTO = categoryUserDTO;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

}
