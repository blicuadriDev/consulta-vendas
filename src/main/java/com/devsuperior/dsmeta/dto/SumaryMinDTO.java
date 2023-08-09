package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SumaryMinProjection;

public class SumaryMinDTO {
	
	private String sellerName;
	private Double total;
	
	public SumaryMinDTO(String sellerName, Double amount) {
		this.sellerName = sellerName;
		this.total = amount;
	}
	
	public SumaryMinDTO(SumaryMinProjection projection) {
		sellerName = projection.getSellerName();
		total = projection.getTotal();
	}
	
	public String getSellerName() {
		return sellerName;
	}

	public Double getAmount() {
		return total;
	}

}
