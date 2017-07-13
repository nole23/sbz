package com.sample.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/*
 * Mesto gde se nalaze gotovi racuni
 */

@Entity
public class AccountFinis {

	@Id
	@GeneratedValue
	private Long id;
	private Date dateCreateAccount; // datum kreiranja racuna
	private Double originalPrice; // originalna cena bez popusta
	private Double newPrice; // nova cena sa uracunatim svim popustima
	private int setPoint; // iskoristeni bodovi za racun
	private int getPoint; // dobijeni bodovi od kupovine
	private String stanje;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private User user;

	@OneToMany(mappedBy = "accountFinis", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<ListItemsAccountFinis> listItemsAccountFinis = new HashSet<ListItemsAccountFinis>();

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

	public String getStanje() {
		return stanje;
	}

	public void setStanje(String stanje) {
		this.stanje = stanje;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<ListItemsAccountFinis> getListItemsAccountFinis() {
		return listItemsAccountFinis;
	}

	public void setListItemsAccountFinis(Set<ListItemsAccountFinis> listItemsAccountFinis) {
		this.listItemsAccountFinis = listItemsAccountFinis;
	}

}
