package com.sample.dto;

public class MessagesDTO {

	private Long id;
	private String message;
	private String error;
	private String jwt;
	private String role;
	private int size;
	private String name;
	private String surname;

	public MessagesDTO(Long id, String message, String error, String jwt, String role, int size, String name,
			String surname) {
		super();
		this.id = id;
		this.message = message;
		this.error = error;
		this.jwt = jwt;
		this.role = role;
		this.size = size;
		this.name = name;
		this.surname = surname;
	}

	public MessagesDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
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

}
