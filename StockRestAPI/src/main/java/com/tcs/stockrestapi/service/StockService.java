package com.tcs.stockrestapi.service;

import java.util.Optional;

import com.tcs.stockrestapi.model.Stock;



public interface StockService {
	
	public Stock createStock(Stock stock);
	public Optional<Stock> getStockById(int id);
	public void deleteStock(int id);
	public Optional<java.util.List<Stock>> getStocks();
	public Optional<java.util.List<Stock>> getStockByLocation(String loaction);
}
