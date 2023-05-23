package io.swagger.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import za.co.gaserve.entities.reports.DailyReport;
import za.co.gaserve.entities.reports.MonthlyReport;
import za.co.gaserve.entities.reports.ReportEntry;

/**
 * Sales
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-12T18:18:01.135Z")

public class ReportDTO {

	public ReportDTO() {
	}

	public ReportDTO(DailyReport report) {
		date = report.getBusinessDay();
		stocktaking = report.getStocktaking() != null?report.getStocktaking().getId():null;
		//stocktakingBalances = report.getStocktaking() != null?report.getStocktaking().getBalances():null;
		stocktakingBalances = true;
		retailer = report.getRetailer().getId();
		user = report.getUser().getId();
		if(report.getReportEntries() != null){
			for(ReportEntry e:report.getReportEntries()){
				addEntry(new ReportEntryDTO(e.getProduct().getId(), e.getQuantity(), e.getTotalAmount(), e.getType()));
			}
		}
	}

	public ReportDTO(MonthlyReport report) {
		date = report.getMonthEnd();
		stocktaking = report.getStocktaking() != null?report.getStocktaking().getId():null;
		//stocktakingBalances = report.getStocktaking() != null?report.getStocktaking().getBalances():null;
		stocktakingBalances = true;
		retailer = report.getRetailer().getId();
		user = report.getUser().getId();
		if(report.getReportEntries() != null){
			for(ReportEntry e:report.getReportEntries()){
				addEntry(new ReportEntryDTO(e.getProduct().getId(), e.getQuantity(), e.getTotalAmount(), e.getType()));
			}
		}		
	}

	@JsonProperty("date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
	private Date date;

	private String stocktaking;

	private boolean stocktakingBalances;

	private String retailer;

	private String user;

	private List<ReportEntryDTO> reportEntries;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStocktaking() {
		return stocktaking;
	}

	public void setStocktaking(String stocktaking) {
		this.stocktaking = stocktaking;
	}

	public boolean isStocktakingBalances() {
		return stocktakingBalances;
	}

	public void setStocktakingBalances(boolean stocktakingBalances) {
		this.stocktakingBalances = stocktakingBalances;
	}

	public String getRetailer() {
		return retailer;
	}

	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<ReportEntryDTO> getReportEntries() {
		return reportEntries;
	}

	public void setReportEntries(List<ReportEntryDTO> reportEntries) {
		this.reportEntries = reportEntries;
	}

	public ReportDTO addEntry(ReportEntryDTO reportEntry) {
		if(this.reportEntries == null ){
			reportEntries = new ArrayList<ReportEntryDTO>();
		}
		reportEntries.add(reportEntry);
		return this;
	}

	
}
