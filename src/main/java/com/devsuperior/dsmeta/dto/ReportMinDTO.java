package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.ReportMinProjection;

public class ReportMinDTO {
	
	private Long id;
	private LocalDate date;
	private Double amount;
	private String sellerName;
	
	
	public ReportMinDTO(Long id, LocalDate date, Double amount, String sellerName) {
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.sellerName = sellerName;
	}
	
	public ReportMinDTO(Sale entity) {
		id = entity.getId();
		date = entity.getDate();
		amount = entity.getAmount();
		sellerName = entity.getSeller().getName();
	}
	
	public ReportMinDTO(ReportMinProjection projection) {
		id = projection.getId();
		date = projection.getDate();
		amount = projection.getAmount();
		sellerName = projection.getName();
	}


	public Long getId() {
		return id;
	}


	public LocalDate getDate() {
		return date;
	}


	public Double getAmount() {
		return amount;
	}


	public String getSellerName() {
		return sellerName;
	}


}
