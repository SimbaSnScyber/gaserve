package io.swagger.model;

import java.util.List;


public class StaticListsDTO   {
	
	public  StaticListsDTO  () {}
	
	public  StaticListsDTO  (
			List<CodeDescriptionDTO> paymentMethods,		
			List<ProductDTO> products,
			List<RegionDTO> regions,
			List<CodeDescriptionDTO> userStatuses) 
	{
		
		this.paymentMethods = paymentMethods;
		this.products = products;
		this.regions = regions;
		this.userStatuses = userStatuses;
	}

	private List<CodeDescriptionDTO> paymentMethods;
	
	private List<ProductDTO> products;

	private List<RegionDTO> regions;

	private List<CodeDescriptionDTO> userStatuses;

	public List<CodeDescriptionDTO> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(List<CodeDescriptionDTO> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}

	public List<RegionDTO> getRegions() {
		return regions;
	}

	public void setRegions(List<RegionDTO> regions) {
		this.regions = regions;
	}

	public List<CodeDescriptionDTO> getUserStatuses() {
		return userStatuses;
	}

	public void setUserStatuses(List<CodeDescriptionDTO> userStatuses) {
		this.userStatuses = userStatuses;
	}	
}

