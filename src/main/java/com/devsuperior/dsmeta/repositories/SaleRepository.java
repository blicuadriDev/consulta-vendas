package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SumaryMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.ReportMinProjection;
import com.devsuperior.dsmeta.projections.SumaryMinProjection;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	// **********NAO OTIMIZADA**********
	@Query("SELECT obj "
			+ "FROM Sale obj "
			+ "WHERE obj.date >= :min "
			+ "AND obj.date <= :max "
			+ "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :sellerName, '%')) ")
	Page<Sale> jpqlReport1 (LocalDate min, LocalDate max, String sellerName, Pageable pageable);

	
	@Query(nativeQuery = true, value = "SELECT tb_sales.id, tb_sales.date, tb_sales.amount, tb_seller.name "
			+"FROM tb_sales "
			+"INNER JOIN tb_seller ON tb_sales.seller_id = tb_seller.id "
			+"WHERE tb_sales.date BETWEEN :min AND :max "
			+"AND UPPER (tb_seller.name) LIKE UPPER(CONCAT('%', :sellerName, '%')) ")
	Page<ReportMinProjection> sqlReport1 (LocalDate min, LocalDate max, String sellerName, Pageable pageable);
	
	
	// **********OTIMIZADA**********
	
	@Query(value = "SELECT obj "
			+"FROM Sale obj "
			+"JOIN FETCH obj.seller "
			+"WHERE (obj.date >= :min AND obj.date <= :max) "
			+"AND (UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :sellerName, '%'))) ",
			countQuery = "SELECT COUNT(obj) "
					+ "FROM Sale obj "
					+ "JOIN obj.seller "
					+ "WHERE (obj.date >= :min AND obj.date <= :max) "
					+ "AND (UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :sellerName, '%')))")
			Page<Sale> jpqlReport2 (LocalDate min, LocalDate max, String sellerName, Pageable pageable);
	

	
	
	
	// **********JAH EH OTIMIZADA**********
	@Query ("SELECT new com.devsuperior.dsmeta.dto.SumaryMinDTO(obj.seller.name, SUM(obj.amount)) "
			+ "FROM Sale obj "
			+ "WHERE obj.date >= :start "
			+ "AND obj.date <= :end "
			+ "GROUP BY obj.seller.name ")
	List<SumaryMinDTO> jpqlSumary1(LocalDate start, LocalDate end);
	
	
	
	// **********JAH EH OTIMIZADA**********
	@Query(nativeQuery = true, value ="SELECT tb_seller.name, SUM(tb_sales.amount) "
		+ "FROM tb_sales "
		+ "INNER JOIN tb_seller ON tb_sales.seller_id = tb_seller.id "
		+ "WHERE tb_sales.date BETWEEN :start AND :end "
		+ "GROUP BY tb_sales.seller.name ")
	List<SumaryMinProjection> sqlSumary1(LocalDate start, LocalDate end);

}
