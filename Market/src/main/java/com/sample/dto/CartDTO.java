package com.sample.dto;

import com.sample.model.Cart;

public class CartDTO {

	private Long id;
	private double price;
	private double discountArticle;
	private int count;
	private double newPrice;
	private ArticleDTO articleDTO;
	private UserDTO userDTO;

	public CartDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartDTO(Long id, double price, double discountArticle, int count, double newPrice, ArticleDTO articleDTO,
			UserDTO userDTO) {
		super();
		this.id = id;
		this.price = price;
		this.discountArticle = discountArticle;
		this.count = count;
		this.newPrice = newPrice;
		this.articleDTO = articleDTO;
		this.userDTO = userDTO;
	}

	public CartDTO(Cart cart) {
		this.id = cart.getId();
		this.price = cart.getPrice();
		this.discountArticle = cart.getDiscountArticle();
		this.count = cart.getCount();
		this.newPrice = cart.getNewPrice();
		if (cart.getArticle() != null)
			this.articleDTO = new ArticleDTO(cart.getArticle());

		if (cart.getUser() != null)
			this.userDTO = new UserDTO(cart.getUser());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscountArticle() {
		return discountArticle;
	}

	public void setDiscountArticle(double discountArticle) {
		this.discountArticle = discountArticle;
	}

	public ArticleDTO getArticleDTO() {
		return articleDTO;
	}

	public void setArticleDTO(ArticleDTO articleDTO) {
		this.articleDTO = articleDTO;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(double newPrice) {
		this.newPrice = newPrice;
	}

}
