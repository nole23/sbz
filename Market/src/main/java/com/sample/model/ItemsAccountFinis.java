package com.sample.model;

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
public class ItemsAccountFinis {

	@Id
	@GeneratedValue
	private Long id;
	private Double originalPrice; // originalna cena artikla uracunat count
	private int count; // kolicina koja se kupuje
	private int discountPrecentage; // popust u procentima
	private Double newPrice;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Article article;

	@OneToMany(mappedBy = "itemsAccountFinis", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<ListItemsAccountFinis> listItemsAccountFinis = new HashSet<ListItemsAccountFinis>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getDiscountPrecentage() {
		return discountPrecentage;
	}

	public void setDiscountPrecentage(int discountPrecentage) {
		this.discountPrecentage = discountPrecentage;
	}

	public Double getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(Double newPrice) {
		this.newPrice = newPrice;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Set<ListItemsAccountFinis> getListItemsAccountFinis() {
		return listItemsAccountFinis;
	}

	public void setListItemsAccountFinis(Set<ListItemsAccountFinis> listItemsAccountFinis) {
		this.listItemsAccountFinis = listItemsAccountFinis;
	}

}
