package com.devsuperior.dsmeta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.ReportMinDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SumaryMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<ReportMinDTO>> getReport(
			@RequestParam(name="minDate", defaultValue="") String minDate, 
			@RequestParam(name="maxDate", defaultValue="") String maxDate,
			@RequestParam(name="name", defaultValue="") String name,
			Pageable pageable) {
		
		Page<ReportMinDTO> report = service.getReport(minDate, maxDate, name, pageable);
		return ResponseEntity.ok(report);
	}
	
	@GetMapping(value = "/reportsql")
	public ResponseEntity<Page<ReportMinDTO>> getReportProjection(
			@RequestParam(name="minDate", defaultValue="") String minDate, 
			@RequestParam(name="maxDate", defaultValue="") String maxDate,
			@RequestParam(name="name", defaultValue="") String name,
			Pageable pageable) {
		
		Page<ReportMinDTO> report = service.getProjectionReport(minDate, maxDate, name, pageable);
		return ResponseEntity.ok(report);
	}
	


	@GetMapping(value = "/summary")
	public ResponseEntity<List<SumaryMinDTO>> getSummary(
			@RequestParam (name ="minDate", defaultValue="") String minDate,
			@RequestParam(name="maxDate", defaultValue="") String maxDate) {
		
		List<SumaryMinDTO> sumary = service.getSumary(minDate, maxDate);
		return ResponseEntity.ok(sumary);
	}
	
	
	@GetMapping(value = "/summarysql")
	public ResponseEntity<List<SumaryMinDTO>> getSumaryProjection(
			@RequestParam(name="minDate", defaultValue="") String minDate, 
			@RequestParam(name="maxDate", defaultValue="") String maxDate) {
		List<SumaryMinDTO> report = service.getProjectionSumary(minDate, maxDate);
		return ResponseEntity.ok(report);
	}
}
