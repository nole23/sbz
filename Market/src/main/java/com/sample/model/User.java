package com.sample.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Profil svih registrovanih na sajtu Ukoliko je registrovan korisnik aplikacije
 * on ima dodtnu tabelu sa podacima gde treba da se posalje naruceni artikl
 * 
 * @author X
 *
 */

@Entity
public class User {

	@Id
	@GeneratedValue
	@Column(unique = true)
	private Long id;
	private String name; // ime
	private String surname; // prezime
	private String email; // email korisnika
	private String username; // korisnicko ime
	private String password; // sifra korisnika
	private Date createProfile; // datum registrovanja korisnika

	@OneToOne(fetch = FetchType.LAZY)
	private ProfileUser profilUser; // Dodatna polja za korisnik

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	private UserRola userRola; // Rola korisnika

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Account> account = new HashSet<Account>();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<AccountFinis> accountFinis = new HashSet<AccountFinis>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<RModel> rModel = new HashSet<RModel>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<OrderArticle> orders = new HashSet<OrderArticle>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Cart> cart = new HashSet<Cart>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateProfile() {
		return createProfile;
	}

	public void setCreateProfile(Date createProfile) {
		this.createProfile = createProfile;
	}

	public ProfileUser getProfilUser() {
		return profilUser;
	}

	public void setProfilUser(ProfileUser profilUser) {
		this.profilUser = profilUser;
	}

	public UserRola getUserRola() {
		return userRola;
	}

	public void setUserRola(UserRola userRola) {
		this.userRola = userRola;
	}

	public Set<Account> getAccount() {
		return account;
	}

	public void setAccount(Set<Account> account) {
		this.account = account;
	}

	public Set<RModel> getrModel() {
		return rModel;
	}

	public void setrModel(Set<RModel> rModel) {
		this.rModel = rModel;
	}

	public Set<OrderArticle> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderArticle> orders) {
		this.orders = orders;
	}

	public Set<Cart> getCart() {
		return cart;
	}

	public void setCart(Set<Cart> cart) {
		this.cart = cart;
	}

	public Set<AccountFinis> getAccountFinis() {
		return accountFinis;
	}

	public void setAccountFinis(Set<AccountFinis> accountFinis) {
		this.accountFinis = accountFinis;
	}
	

}
