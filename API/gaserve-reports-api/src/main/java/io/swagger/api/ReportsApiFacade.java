package io.swagger.api;

import java.util.List;

import io.swagger.model.ErrorDTO;
import io.swagger.model.StockDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import za.co.gaserve.entities.BusinessException;
import za.co.gaserve.entities.reports.DailyReport;
import za.co.gaserve.entities.reports.MonthlyReport;

import javax.validation.Valid;
import java.util.List;
import java.util.Timer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import co.za.gaserve.business.ListBusinessService;
import co.za.gaserve.business.ReportsBusinessService;
import co.za.gaserve.business.ReportsBusinessService;
import co.za.gaserve.business.RetailerBusinessService;
import co.za.gaserve.business.StockBusinessService;
import co.za.gaserve.business.UserBusinessService;
import io.swagger.model.SalesReportDTO;
import io.swagger.model.SalesReportDTO;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.reports.DailyReport;
import za.co.gaserve.entities.reports.DailyReport;
import za.co.gaserve.entities.reports.MonthlyReport;
import za.co.gaserve.entities.reports.MonthlyReport;
import za.co.gaserve.entities.stock.Product;
import za.co.gaserve.entities.stock.Stock;
import za.co.gaserve.entities.stock.StocktakingEntry;
import za.co.gaserve.entities.user.User;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-12T18:18:01.135Z")

@Controller
public class ReportsApiFacade implements ReportsApi {

    private static final Logger log = LoggerFactory.getLogger(ReportsApiFacade.class);

    @Autowired
    private ReportsBusinessService reportsBusinessService; 
 
    @Autowired
    private RetailerBusinessService retialerBusinessService; 

    @Autowired
    private UserBusinessService userBusinessService; 

    @Autowired
    private ListBusinessService listBusinessService;



    @Autowired
    private StockBusinessService stockBusinessService; 

    @GetMapping("/reports")
    public ResponseEntity<?> searchReport(@RequestHeader("userId") String userId) {


    	try {

    		User user = userBusinessService.findById(userId);

    		if (user == null){
    		    throw new BusinessException("User does not exist",HttpStatus.NOT_FOUND);
            }

    		Retailer retailer = retialerBusinessService.getRetailerByUser(userId);

			if (retailer == null){
				throw new BusinessException("user not linked to a retailer", HttpStatus.NOT_FOUND);
			}

    		List<Product> products = listBusinessService.getProducts();

			reportsBusinessService.closeAllDaysBeforeToday(user, retailer);

			List<DailyReport> pastDays = reportsBusinessService.getPastDays(products,user,retailer, 2);
			List<MonthlyReport> pastMonths = reportsBusinessService.getPastMonths(products,user,retailer,2);
    		List<Retailer> lockedRetailers = reportsBusinessService.getLockedRetailers();
    		List<Stock> availableStock = stockBusinessService.findStockByRetailer(retailer);
    		List<Stock> opeingingStock = stockBusinessService.findOpeningStockByRetailer(retailer);
    		List<Stock> receivedStock = stockBusinessService.findReceivedStockByRetailer(retailer);


    		return new ResponseEntity<SalesReportDTO>
					(
							new SalesReportDTO()
									.addDailyReports(pastDays)
									.addMonthlyReports(pastMonths)
									.addLockedRetailers(lockedRetailers)
									.addAvailableStock(availableStock)
									.addOpeningStock(opeingingStock)
									.addReceivedStock(receivedStock)
					,HttpStatus.OK);
    		
    		//*******NOTE DO NOT Delete the below. Beths must manage as action items
    		//TODO:App must provide unlock functionality otherwise users are locked out. Cannot be manual unless coded in a utility
    		//TODO:Add a check for role of User and olny return locked users for administrators
    		//TODO:Add Recieved today and opening stock to report
    		//TODO:Stocktaking must run reportsBusinessService.closeAllDaysBeforeToday as done here.
    		//TODO:Test getSales by date range: critical for report
    		//TODO:See DaoImpl.getByField I do not think it works
    		//TODO:Bootstrap Sales,DailyReport,MontlyReport,LockedRetailer/Users then run this API get to pull them
    		//TODO:Test SalesDaoImpl.getSalesForDay have sales over 3 days bootstrapped for test then select day in middle
    		//TODO:Test SalesDaoImpl Query By related object
			//
    	}catch (BusinessException be){
    		ErrorDTO errorDTO = new ErrorDTO();
    		errorDTO.setErrorMessage(be.getMessage());
    		errorDTO.setErrorCode(be.getStatusCode().toString());
    		return new ResponseEntity<ErrorDTO>(errorDTO,be.getStatusCode());
		}
    	catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
				ErrorDTO errorDTO = new ErrorDTO();
				errorDTO.setErrorMessage("Something unexpected went wrong");
				errorDTO.setErrorCode("500");
                return new ResponseEntity<>(errorDTO,HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
}
