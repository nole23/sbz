package com.sample.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.sample.model.Article;

public class ArticleDTO {

	private Long id;
	private String nameArticle;
	private Double price;
	private int amount;
	private Date dataAddArticle;
	private boolean complement;
	private boolean statusRecord;
	private int minState;
	private PodCategoryDTO podCategoryDTO;
	private Set<ItemsAccountDTO> atemsAccountDTO = new HashSet<ItemsAccountDTO>();
	private Set<CartDTO> cartDTO = new HashSet<CartDTO>();

	public ArticleDTO() {
	}

	public ArticleDTO(Long id, String nameArticle, Double price, int amount, Date dataAddArticle, boolean complement,
			boolean statusRecord, int minState, PodCategoryDTO podCategoryDTO, Set<ItemsAccountDTO> atemsAccountDTO,
			Set<CartDTO> cartDTO) {
		super();
		this.id = id;
		this.nameArticle = nameArticle;
		this.price = price;
		this.amount = amount;
		this.dataAddArticle = dataAddArticle;
		this.complement = complement;
		this.statusRecord = statusRecord;
		this.minState = minState;
		this.podCategoryDTO = podCategoryDTO;
		this.atemsAccountDTO = atemsAccountDTO;
		this.cartDTO = cartDTO;
	}

	public ArticleDTO(Article article) {
		this.id = article.getId();
		this.nameArticle = article.getNameArticle();
		this.price = article.getPrice();
		this.amount = article.getAmount();
		this.dataAddArticle = article.getDataAddArticle();
		this.complement = article.isComplement();
		this.statusRecord = article.isStatusRecord();
		this.minState = article.getMinState();
		if (article.getPodCategory() != null)
			this.podCategoryDTO = new PodCategoryDTO(article.getPodCategory());
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getDataAddArticle() {
		return dataAddArticle;
	}

	public void setDataAddArticle(Date dataAddArticle) {
		this.dataAddArticle = dataAddArticle;
	}

	public boolean isComplement() {
		return complement;
	}

	public void setComplement(boolean complement) {
		this.complement = complement;
	}

	public boolean isStatusRecord() {
		return statusRecord;
	}

	public void setStatusRecord(boolean statusRecord) {
		this.statusRecord = statusRecord;
	}

	public int getMinState() {
		return minState;
	}

	public void setMinState(int minState) {
		this.minState = minState;
	}

	public PodCategoryDTO getPodCategoryDTO() {
		return podCategoryDTO;
	}

	public void setPodCategoryDTO(PodCategoryDTO podCategoryDTO) {
		this.podCategoryDTO = podCategoryDTO;
	}

	public Set<ItemsAccountDTO> getAtemsAccountDTO() {
		return atemsAccountDTO;
	}

	public void setAtemsAccountDTO(Set<ItemsAccountDTO> atemsAccountDTO) {
		this.atemsAccountDTO = atemsAccountDTO;
	}

	public Set<CartDTO> getCartDTO() {
		return cartDTO;
	}

	public void setCartDTO(Set<CartDTO> cartDTO) {
		this.cartDTO = cartDTO;
	}

}
