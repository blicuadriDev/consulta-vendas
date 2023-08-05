package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.ReportMinDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SumaryMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	
	
	public Page<ReportMinDTO> getReport (String minDate, String maxDate, String sellerName, Pageable pageable){
		
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		
		LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);
		LocalDate min = minDate.equals("") ? max.minusYears(1L) : LocalDate.parse(minDate);
		
		Page<Sale> result = repository.searchSales(min, max, sellerName, pageable);
		
		return result.map(x -> new ReportMinDTO(x));
	}
	
	
	public List<SumaryMinDTO> getSumary (String minDate, String maxDate){
		
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		
		LocalDate endDate = maxDate.equals("") ? today : LocalDate.parse(maxDate);
		LocalDate startDate = minDate.equals("") ? endDate.minusYears(1L) : LocalDate.parse(minDate);
		
		List<SumaryMinDTO> result = repository.sumarySale(startDate, endDate);
		
		return result;
	}
	
	
}
