package com.sample.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.sample.model.User;

public class UserDTO {

	private Long id;
	private String name;
	private String surname;
	private String email;
	private String username;
	private String password;
	private Date createProfile;
	private ProfileUserDTO profilUserDTO;
	private Set<UserRolaDTO> userRolaDTO = new HashSet<UserRolaDTO>();
	private Set<AccountDTO> accountDTO = new HashSet<AccountDTO>();
	private Set<CartDTO> cartDTO = new HashSet<CartDTO>();

	public UserDTO() {
	}

	public UserDTO(Long id, String name, String surname, String email, String username, String password,
			Date createProfile, ProfileUserDTO profilUserDTO, Set<UserRolaDTO> userRolaDTO, Set<AccountDTO> accountDTO,
			Set<CartDTO> cartDTO) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.createProfile = createProfile;
		this.profilUserDTO = profilUserDTO;
		this.userRolaDTO = userRolaDTO;
		this.accountDTO = accountDTO;
		this.cartDTO = cartDTO;
	}

	public UserDTO(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.email = user.getEmail();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.createProfile = user.getCreateProfile();
		if (user.getProfilUser() != null)
			this.profilUserDTO = new ProfileUserDTO(user.getProfilUser());
	}

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

	public ProfileUserDTO getProfilUserDTO() {
		return profilUserDTO;
	}

	public void setProfilUserDTO(ProfileUserDTO profilUserDTO) {
		this.profilUserDTO = profilUserDTO;
	}

	public Set<UserRolaDTO> getUserRolaDTO() {
		return userRolaDTO;
	}

	public void setUserRolaDTO(Set<UserRolaDTO> userRolaDTO) {
		this.userRolaDTO = userRolaDTO;
	}

	public Set<AccountDTO> getAccountDTO() {
		return accountDTO;
	}

	public void setAccountDTO(Set<AccountDTO> accountDTO) {
		this.accountDTO = accountDTO;
	}

	public Set<CartDTO> getCartDTO() {
		return cartDTO;
	}

	public void setCartDTO(Set<CartDTO> cartDTO) {
		this.cartDTO = cartDTO;
	}

}
