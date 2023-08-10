package com.devsuperior.dsmeta.projections;

import java.time.LocalDate;

public interface ReportMinProjection {
	
	Long getId();
	Double getAmount();
	LocalDate getDate();
	String getName();

}
