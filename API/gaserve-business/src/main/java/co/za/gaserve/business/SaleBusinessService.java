package co.za.gaserve.business;

import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.sales.Sale;
import za.co.gaserve.entities.stock.Product;

public interface SaleBusinessService {
	void sell(Sale sale) throws Exception;
	void reduceStock(Retailer retailer, Product product, Double amount) throws Exception;
}
