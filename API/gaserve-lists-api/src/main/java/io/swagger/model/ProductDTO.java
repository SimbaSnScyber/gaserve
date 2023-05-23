package io.swagger.model;

import za.co.gaserve.entities.stock.Product;


public class ProductDTO   {

	public ProductDTO(){		
	}

	public ProductDTO(Product product){
		this.id = product.getId();
		this.description = product.getDescription();
		this.price = product.getPrice();
	}

	private String id;
    private String description;
    private double price;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}

