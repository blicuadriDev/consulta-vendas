package com.devsuperior.dsmeta.dto;

public class SumaryMinDTO {
	
	private String sellerName;
	private Double total;
	
	public SumaryMinDTO(String sellerName, Double amount) {
		this.sellerName = sellerName;
		this.total = amount;
	}
	
	public String getSellerName() {
		return sellerName;
	}

	public Double getAmount() {
		return total;
	}

}
