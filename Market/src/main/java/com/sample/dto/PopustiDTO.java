package com.sample.dto;

import com.sample.model.Popusti;

public class PopustiDTO {

	private Long id;
	private String tipPopusta;
	private int procenat;
	private AccountDTO accountDTO;

	public PopustiDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PopustiDTO(Long id, String tipPopusta, int procenat, AccountDTO accountDTO) {
		super();
		this.id = id;
		this.tipPopusta = tipPopusta;
		this.procenat = procenat;
		this.accountDTO = accountDTO;
	}
	
	public PopustiDTO(Popusti p) {
		this.id = p.getId();
		this.tipPopusta = p.getTipPopusta();
		this.procenat = p.getProcenat();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipPopusta() {
		return tipPopusta;
	}

	public void setTipPopusta(String tipPopusta) {
		this.tipPopusta = tipPopusta;
	}

	public int getProcenat() {
		return procenat;
	}

	public void setProcenat(int procenat) {
		this.procenat = procenat;
	}

	public AccountDTO getAccountDTO() {
		return accountDTO;
	}

	public void setAccountDTO(AccountDTO accountDTO) {
		this.accountDTO = accountDTO;
	}

}
