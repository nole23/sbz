package com.sample.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.sample.model.Account;
import com.sample.model.ListItemsAccount;
import com.sample.model.Popusti;

public class AccountDTO {

	private Long id;
	private Date dateCreateAccount;
	private Double originalPrice;
	private Double newPrice;
	private int setPoint;
	private int getPoint;
	private UserDTO userDTO;
	private Set<ListItemsAccountDTO> listItemsAccountDTO = new HashSet<>();
	private String stanje;
	private Set<PopustiDTO> popustiDTO = new HashSet<PopustiDTO>();

	public AccountDTO() {
	}

	public AccountDTO(Long id, Date dateCreateAccount, Double originalPrice, Double newPrice, int setPoint,
			int getPoint, UserDTO userDTO, Set<ListItemsAccountDTO> listItemsAccountDTO, String stanje,
			Set<PopustiDTO> popustiDTO) {
		super();
		this.id = id;
		this.dateCreateAccount = dateCreateAccount;
		this.originalPrice = originalPrice;
		this.newPrice = newPrice;
		this.setPoint = setPoint;
		this.getPoint = getPoint;
		this.userDTO = userDTO;
		this.listItemsAccountDTO = listItemsAccountDTO;
		this.stanje = stanje;
	}

	public AccountDTO(Account account) {
		super();
		this.id = account.getId();
		this.dateCreateAccount = account.getDateCreateAccount();
		this.originalPrice = account.getOriginalPrice();
		this.newPrice = account.getNewPrice();
		this.setPoint = account.getSetPoint();
		this.getPoint = account.getGetPoint();
		if (account.getUser() != null)
			this.userDTO = new UserDTO(account.getUser());
		if (account.getListItemsAccount() != null) {
			this.listItemsAccountDTO = new HashSet<>();
			for (ListItemsAccount ia : account.getListItemsAccount()) {
				this.listItemsAccountDTO.add(new ListItemsAccountDTO(ia));
			}
		}
		this.stanje = account.getStanje();
		if (account.getPopusti() != null) {
			this.popustiDTO = new HashSet<>();
			for (Popusti p : account.getPopusti()) {
				this.popustiDTO.add(new PopustiDTO(p));
			}
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateCreateAccount() {
		return dateCreateAccount;
	}

	public void setDateCreateAccount(Date dateCreateAccount) {
		this.dateCreateAccount = dateCreateAccount;
	}

	public Double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Double getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(Double newPrice) {
		this.newPrice = newPrice;
	}

	public int getSetPoint() {
		return setPoint;
	}

	public void setSetPoint(int setPoint) {
		this.setPoint = setPoint;
	}

	public int getGetPoint() {
		return getPoint;
	}

	public void setGetPoint(int getPoint) {
		this.getPoint = getPoint;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public Set<ListItemsAccountDTO> getListItemsAccountDTO() {
		return listItemsAccountDTO;
	}

	public void setListItemsAccountDTO(Set<ListItemsAccountDTO> listItemsAccountDTO) {
		this.listItemsAccountDTO = listItemsAccountDTO;
	}

	public String getStanje() {
		return stanje;
	}

	public void setStanje(String stanje) {
		this.stanje = stanje;
	}

	public Set<PopustiDTO> getPopustiDTO() {
		return popustiDTO;
	}

	public void setPopustiDTO(Set<PopustiDTO> popustiDTO) {
		this.popustiDTO = popustiDTO;
	}
}
