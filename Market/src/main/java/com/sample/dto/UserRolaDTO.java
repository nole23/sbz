package com.sample.dto;

import com.sample.model.UserRola;

public class UserRolaDTO {

	private Long id;
	private RoleDTO roleDTO;
	private UserDTO userDTO;

	public UserRolaDTO() {
	}

	public UserRolaDTO(Long id, RoleDTO roleDTO, UserDTO userDTO) {
		super();
		this.id = id;
		this.roleDTO = roleDTO;
		this.userDTO = userDTO;
	}

	public UserRolaDTO(UserRola userRola) {
		super();
		this.id = userRola.getId();
		if (userRola.getRole() != null)
			this.roleDTO = new RoleDTO(userRola.getRole());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleDTO getRoleDTO() {
		return roleDTO;
	}

	public void setRoleDTO(RoleDTO roleDTO) {
		this.roleDTO = roleDTO;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

}
