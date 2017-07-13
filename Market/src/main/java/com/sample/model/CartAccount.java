package com.sample.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CartAccount {

	@Id
	@GeneratedValue
	private Long id;
	private double cenaPopust;		//cena sa popustom ukupna
	private double ukupno;			//cena ukupna bez popusta

	
}
