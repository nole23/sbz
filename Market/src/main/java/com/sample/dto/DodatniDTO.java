package com.sample.dto;

public class DodatniDTO {

	private Long id;
	private Long idKat;
	private Double count;

	public DodatniDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DodatniDTO(Long id, Long idKat, Double count) {
		super();
		this.id = id;
		this.idKat = idKat;
		this.count = count;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdKat() {
		return idKat;
	}

	public void setIdKat(Long idKat) {
		this.idKat = idKat;
	}

	public Double getCount() {
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
	}
}
