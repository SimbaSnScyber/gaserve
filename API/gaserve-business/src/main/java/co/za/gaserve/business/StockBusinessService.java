package co.za.gaserve.business;

import java.util.List;

import java.util.Date;
import java.util.List;

import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.stock.*;

import java.util.List;

public interface StockBusinessService {

	void balance(String stocktakingId,String userId,String reason);
	void receive(Receiving receiving, Retailer retailer);
	void receiveItem(ReceivedItem receiving, Retailer retailer);

	Stock findById(String id);
	List<Stock> findAll();
	Stocktaking takeStock(Stocktaking stocktaking);
	List<Stock> findByDate(java.util.Date date);
	List<Stock> findStockByRetailer(Retailer retailer);
	List<Stock> findOpeningStockByRetailer(Retailer retailer);
	List<Stock> findReceivedStockByRetailer(Retailer retailer);
}