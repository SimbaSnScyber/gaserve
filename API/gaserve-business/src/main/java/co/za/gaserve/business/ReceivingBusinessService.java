package co.za.gaserve.business;

import java.util.List;

import za.co.gaserve.entities.stock.Product;
import za.co.gaserve.entities.stock.Receiving;

public interface ReceivingBusinessService {
	List<Receiving> getByProduct(Product product);
	Product getProductByName(String name);
	void receive(List<Receiving> receiving);
	void receive(Receiving receiving);
}
