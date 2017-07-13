package com.sample.dto;

import java.util.HashSet;
import java.util.Set;

import com.sample.model.Role;

public class RoleDTO {

	private Long id;
	private String name;
	private Set<UserRolaDTO> userRolaDTO = new HashSet<UserRolaDTO>();

	public RoleDTO() {
	}

	public RoleDTO(Long id, String name, Set<UserRolaDTO> userRolaDTO) {
		super();
		this.id = id;
		this.name = name;
		this.userRolaDTO = userRolaDTO;
	}

	public RoleDTO(Role role) {
		this.id = role.getId();
		this.name = role.getName();
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

	public Set<UserRolaDTO> getUserRolaDTO() {
		return userRolaDTO;
	}

	public void setUserRolaDTO(Set<UserRolaDTO> userRolaDTO) {
		this.userRolaDTO = userRolaDTO;
	}

}
