package com.sample.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Uloge na sistemu
 * 
 * @author X
 *
 */

@Entity
public class Role {

	@Id
	@GeneratedValue
	@Column(unique = true)
	private Long id;
	private String name;

	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<UserRola> userRole = new HashSet<UserRola>();

	public Set<UserRola> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRola> userRole) {
		this.userRole = userRole;
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

}
