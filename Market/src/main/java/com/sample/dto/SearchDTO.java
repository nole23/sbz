package com.sample.dto;

public class SearchDTO {

	private Long id;
	private String nameArticle;
	private Long podCategory;
	private double minCena;
	private double maxCena;

	public SearchDTO() {
		// TODO Auto-generated constructor stub
	}

	public SearchDTO(Long id, String nameArticle, Long podCategory, double minCena, double maxCena) {
		super();
		this.id = id;
		this.nameArticle = nameArticle;
		this.podCategory = podCategory;
		this.minCena = minCena;
		this.maxCena = maxCena;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameArticle() {
		return nameArticle;
	}

	public void setNameArticle(String nameArticle) {
		this.nameArticle = nameArticle;
	}

	public Long getPodCategory() {
		return podCategory;
	}

	public void setPodCategory(Long podCategory) {
		this.podCategory = podCategory;
	}

	public double getMinCena() {
		return minCena;
	}

	public void setMinCena(double minCena) {
		this.minCena = minCena;
	}

	public double getMaxCena() {
		return maxCena;
	}

	public void setMaxCena(double maxCena) {
		this.maxCena = maxCena;
	}

}
