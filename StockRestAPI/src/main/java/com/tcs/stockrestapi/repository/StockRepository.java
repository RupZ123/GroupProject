package com.tcs.stockrestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.stockrestapi.model.Stock;



@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

	List<Stock> findByLocation(String loaction);
	
	

}
