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

/**
 * Ceo racun koji ne mora biti kupljne ima tri stanja poruceno, otkazano uspesno
 * realizovana u porucenom stanju on dodaje u korpu otkazano on je odustao o
 * kupovine a realizovano znaci da je potvrdio kupovinu i racun se kopira u novu
 * tabelu koja ce cuvati sve kupovine za odredjenog korisnika treba to
 * realizovati kasnije
 * 
 * @author X
 *
 */

@Entity
public class Account {

	@Id
	@GeneratedValue
	private Long id;
	private Date dateCreateAccount; // datum kreiranja racuna
	private Double originalPrice; // originalna cena bez popusta
	private Double newPrice; // nova cena sa uracunatim svim popustima
	private int setPoint; // iskoristeni bodovi za racun
	private int getPoint; // dobijeni bodovi od kupovine
	private String stanje; /*
							 * <- stanje racuna moze da bude Poruceno, Otkazano
							 * ili uspesno realizovano Ukoliko je racun porucen,
							 * jos uvek se puni u korpu, ako je otkazan taj
							 * racun vise ne moze da se menja i korisnik taj
							 * racun vidi samo u spisku racuna ukoliko je
							 * uspesno realizovano to znaci da je kupljeno i da
							 * je placeno
							 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private User user;

	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<ListItemsAccount> listItemsAccount = new HashSet<ListItemsAccount>();

	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Popusti> popusti = new HashSet<Popusti>();

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<ListItemsAccount> getListItemsAccount() {
		return listItemsAccount;
	}

	public void setListItemsAccount(Set<ListItemsAccount> listItemsAccount) {
		this.listItemsAccount = listItemsAccount;
	}

	public String getStanje() {
		return stanje;
	}

	public void setStanje(String stanje) {
		this.stanje = stanje;
	}

	public Set<Popusti> getPopusti() {
		return popusti;
	}

	public void setPopusti(Set<Popusti> popusti) {
		this.popusti = popusti;
	}

}
