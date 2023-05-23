package io.swagger.model;

import za.co.gaserve.entities.reports.ReportEntry.EntryType;

//---------Killer code
//Verbose interfaces pattern as per Martin Fowler https://martinfowler.com/bliki/FluentInterface.html

public class ReportEntryDTO {

	public ReportEntryDTO(){}
	
	public ReportEntryDTO(
			String product,
			double quantity,
			double totalAmount,
			EntryType entryType
			)
	{
		this.productId = product;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
		this.entryType = entryType;
		
	}
	
	private String productId;
	private double quantity;
	private double totalAmount;
	private EntryType entryType;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public EntryType getentryType() {
		return entryType;
	}
	public void setEntryType(EntryType entryType) {
		this.entryType = entryType;
	}

	

}
