package com.sample.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Ovde ide akcijski dogadja na primer 
 * Letnja rasprodaja koja traje od 4.7 - 14.7 i odredjen procenat popusta koji je definisan 
 * za taj period
 * 
 * @author X
 *
 */

@Entity
public class ActionEvent {

	@Id
	@GeneratedValue
	private Long id;
	private String nameAction;	// Naziv dogadjana
	private Date startDate;		// pocetak dogadjaja
	private Date endDate;		// kraj dogadjaj
	private int procent;		// Procenat popusta na dati dogadjaj

	@OneToMany(mappedBy = "actionEvent", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<ListActionCategory> listActionCategory = new HashSet<ListActionCategory>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameAction() {
		return nameAction;
	}

	public void setNameAction(String nameAction) {
		this.nameAction = nameAction;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getProcent() {
		return procent;
	}

	public void setProcent(int procent) {
		this.procent = procent;
	}

	public Set<ListActionCategory> getListActionCategory() {
		return listActionCategory;
	}

	public void setListActionCategory(Set<ListActionCategory> listActionCategory) {
		this.listActionCategory = listActionCategory;
	}

}
