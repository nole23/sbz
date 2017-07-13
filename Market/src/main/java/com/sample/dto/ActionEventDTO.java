package com.sample.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.sample.model.ActionEvent;

public class ActionEventDTO {

	private Long id;
	private String nameAction;
	private Date startDate;
	private Date endDate;
	private int procent;
	private Long kategorija;
	private Set<ListActionCategoryDTO> listActionCategoryDTO = new HashSet<ListActionCategoryDTO>();

	public ActionEventDTO() {
	}

	public ActionEventDTO(Long id, String nameAction, Date startDate, Date endDate, int procent,
			Set<ListActionCategoryDTO> listActionCategoryDTO) {
		super();
		this.id = id;
		this.nameAction = nameAction;
		this.startDate = startDate;
		this.endDate = endDate;
		this.procent = procent;
		this.listActionCategoryDTO = listActionCategoryDTO;
	}

	public ActionEventDTO(ActionEvent actionEvent) {
		this.id = actionEvent.getId();
		this.nameAction = actionEvent.getNameAction();
		this.startDate = actionEvent.getStartDate();
		this.endDate = actionEvent.getEndDate();
		this.procent = actionEvent.getProcent();
	}

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

	public Set<ListActionCategoryDTO> getListActionCategoryDTO() {
		return listActionCategoryDTO;
	}

	public void setListActionCategoryDTO(Set<ListActionCategoryDTO> listActionCategoryDTO) {
		this.listActionCategoryDTO = listActionCategoryDTO;
	}

	public Long getKategorija() {
		return kategorija;
	}

	public void setKategorija(Long kategorija) {
		this.kategorija = kategorija;
	}

}
