package za.co.gaserve.entities.regions;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RetailerFilter {

	public static <T extends Retailed> List<T> filterByRetailer(Retailer retailer,List<T> sales){
		
		List<T> salesTmp = new ArrayList<T>();
		
		for(T sale:sales){
			if(!StringUtils.isEmpty(retailer.getId()) && retailer.getId().equals(sale.getRetailer().getId())){
				salesTmp.add(sale);
			}
		}
		return salesTmp;
	}

}
