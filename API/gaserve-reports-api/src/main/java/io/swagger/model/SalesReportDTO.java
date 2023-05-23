package io.swagger.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.reports.DailyReport;
import za.co.gaserve.entities.reports.MonthlyReport;
import za.co.gaserve.entities.stock.Stock;
import za.co.gaserve.entities.stock.StocktakingEntry;

/**
 * SalesReport
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-12T18:18:01.135Z")

//---------Killer code
//Verbose interfaces pattern as per Martin Fowler https://martinfowler.com/bliki/FluentInterface.html
public class SalesReportDTO {

	private List<LockedRetailerDTO> lockedRetailers;

	private List<ReportDTO> dailyReports;

	private List<ReportDTO> monthlyReports;

	private List<StockDTO> receivedToday;

	private List<StockDTO> openingStock;

	private List<StockDTO> availableStock;

	public List<LockedRetailerDTO> getLockedRetailers() {
		return lockedRetailers;
	}

	public void setLockedRetailers(List<LockedRetailerDTO> lockedRetailers) {
		this.lockedRetailers = lockedRetailers;
	}

	public SalesReportDTO addLockedRetailers(List<Retailer> lockedRetailers) {
		for(Retailer lockedRetailer:lockedRetailers){
			addLockedRetailer(new LockedRetailerDTO(lockedRetailer.getManager().getId(), lockedRetailer.getId()));
		}
		return this;
	}

	public SalesReportDTO addLockedRetailer(LockedRetailerDTO lockedRetailer) {
		if(this.lockedRetailers == null){
			this.lockedRetailers = new ArrayList<LockedRetailerDTO>();
		}
		this.lockedRetailers.add(lockedRetailer);
		return this;
	}

	public List<ReportDTO> getDailyReports() {
		return dailyReports;
	}

	public void setDailyReports(List<ReportDTO> dailyReports) {
		this.dailyReports = dailyReports;
	}

	public SalesReportDTO addDailyReports(List<DailyReport> dailyReports) {
		for(DailyReport dailyReport:dailyReports){
			addDailyReport(new ReportDTO(dailyReport));
		}
		return this;
	}

	public SalesReportDTO addMonthlyReports(List<MonthlyReport> monthlyReports) {
		for(MonthlyReport monthlyReport:monthlyReports){
			addMonthlyReport(new ReportDTO(monthlyReport));
		}
		return this;
	}

	public SalesReportDTO addDailyReport(ReportDTO dailyReport) {
		if(this.dailyReports == null){
			this.dailyReports = new ArrayList<ReportDTO>();
		}
		this.dailyReports.add(dailyReport);
		return this;
	}

	public List<ReportDTO> getMonthlyReports() {
		return monthlyReports;
	}

	public void setMonthlyReports(List<ReportDTO> monthlyReports) {
		this.monthlyReports = monthlyReports;
	}
	public SalesReportDTO addMonthlyReport(ReportDTO monthlyReport) {
		if(this.monthlyReports == null){
			this.monthlyReports = new ArrayList<ReportDTO>();
		}
		this.monthlyReports.add(monthlyReport);
		return this;
	}

	public List<StockDTO> getReceivedToday() {
		return receivedToday;
	}

	public void setReceivedToday(List<StockDTO> receivedToday) {
		this.receivedToday = receivedToday;
	}

	public void addReceivedToday(StockDTO receivedToday) {
		if(this.receivedToday == null){
			this.receivedToday = new ArrayList<StockDTO>();
		}
		this.receivedToday.add(receivedToday);
	}

	public List<StockDTO> getOpeningStock() {
		return openingStock;
	}

	public void setOpeningStock(List<StockDTO> openingStock) {
		this.openingStock = openingStock;
	}

	public List<StockDTO> getAvailableStock() {
		return availableStock;
	}

	public void setAvailableStock(List<StockDTO> availableStock) {
		this.availableStock = availableStock;
	}
	
	public void addAvailableStock(StockDTO stock) {
		if(this.availableStock == null){
			this.availableStock = new ArrayList<StockDTO>();
		}
		this.availableStock.add(stock);
	}

	public void addOpeningStock(StockDTO stock){
		if(this.openingStock == null){
			this.openingStock = new ArrayList<StockDTO>();
		}
		this.openingStock.add(stock);

	}


	public void addReceivedStock(StockDTO stock){
		if(this.receivedToday == null){
			this.receivedToday = new ArrayList<StockDTO>();
		}
		this.receivedToday.add(stock);

	}

	public SalesReportDTO addAvailableStock(List<Stock> stocks) {
		for(Stock stock:stocks){
			addAvailableStock(new StockDTO(stock.getId(), stock.getProduct().getId(),stock.getQuantity()));
		}
		return this;
	}

	public SalesReportDTO addOpeningStock(List<Stock> stocks){

		if (stocks == null){
			return this;
		}
		for(Stock stock:stocks){
			addOpeningStock(new StockDTO(stock.getId(), stock.getProduct().getId(),stock.getQuantity()));
		}
		return this;
	}

	public SalesReportDTO addReceivedStock(List<Stock> stocks){

		if (stocks == null){
			return this;
		}
		for(Stock stock:stocks){
			addReceivedStock(new StockDTO(stock.getId(), stock.getProduct().getId(),stock.getQuantity()));
		}
		return this;
	}

}
