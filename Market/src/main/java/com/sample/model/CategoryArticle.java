package com.sample.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Ovde je definisana kategorija nase artikle treba proveriti oko definisanja
 * maksimalno dozvoljenog popusta za artikle date kategorije
 * 
 * @author X
 *
 */

@Entity
public class CategoryArticle {

	@Id
	@GeneratedValue
	private Long id;
	private String uniqueCode;// jedinstvena sifra
	private String nameCategory;
	private boolean sirokePotrosnje;
	private int maxDiscount;

	@OneToMany(mappedBy = "categoryArticle", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<PodCategory> podCategory = new HashSet<PodCategory>();

	@OneToMany(mappedBy = "categoryArticle", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<ListActionCategory> listActionCategory = new HashSet<ListActionCategory>();

	@OneToMany(mappedBy = "categoryArticle", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<RModel> rModel = new HashSet<RModel>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameCategory() {
		return nameCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}

	public Set<ListActionCategory> getListActionCategory() {
		return listActionCategory;
	}

	public void setListActionCategory(Set<ListActionCategory> listActionCategory) {
		this.listActionCategory = listActionCategory;
	}

	public Set<RModel> getrModel() {
		return rModel;
	}

	public void setrModel(Set<RModel> rModel) {
		this.rModel = rModel;
	}

	public String getUniqueCode() {
		return uniqueCode;
	}

	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}

	public boolean isSirokePotrosnje() {
		return sirokePotrosnje;
	}

	public void setSirokePotrosnje(boolean sirokePotrosnje) {
		this.sirokePotrosnje = sirokePotrosnje;
	}

	public int getMaxDiscount() {
		return maxDiscount;
	}

	public void setMaxDiscount(int maxDiscount) {
		this.maxDiscount = maxDiscount;
	}

	public Set<PodCategory> getPodCategory() {
		return podCategory;
	}

	public void setPodCategory(Set<PodCategory> podCategory) {
		this.podCategory = podCategory;
	}

}
