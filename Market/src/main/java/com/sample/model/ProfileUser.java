package com.sample.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Podaci o korisniku koji aplikacije
 * @author X
 *
 */

@Entity
public class ProfileUser {

	@Id
	@GeneratedValue
	private Long id;
	private String country;
	private String city;
	private String street;
	private String numberStreet;
	private int rewardPoints;

	@OneToOne(fetch = FetchType.LAZY)
	private CategoryUser categoryUser;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "profilUser", cascade = CascadeType.ALL)
	private User user;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CategoryUser getCategoryUser() {
		return categoryUser;
	}

	public void setCategoryUser(CategoryUser categoryUser) {
		this.categoryUser = categoryUser;
	}

}
