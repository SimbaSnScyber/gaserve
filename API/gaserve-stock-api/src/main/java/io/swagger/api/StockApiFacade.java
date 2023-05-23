package io.swagger.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import co.za.gaserve.business.*;
import io.swagger.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiParam;
import io.swagger.model.StocktakingDTO;
import za.co.gaserve.entities.BusinessException;
import za.co.gaserve.entities.lists.UserStatus;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.sales.Sale;
import za.co.gaserve.entities.stock.Product;
import za.co.gaserve.entities.stock.Stock;
import za.co.gaserve.entities.stock.Stocktaking;
import za.co.gaserve.entities.stock.StocktakingEntry;
import za.co.gaserve.entities.user.User;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-10T20:38:27.309Z")

@Controller
public class StockApiFacade {

    private static final Logger log = LoggerFactory.getLogger(StockApiFacade.class);

    @Autowired
    private StockBusinessService stockBusinessService;

    @Autowired
    private UserBusinessService userBusinessService;

    @Autowired
    private RetailerBusinessService retailerBusinessService;

    @Autowired
    private ListBusinessService listBusinessService;

    @Autowired
    private SaleBusinessService saleBusinessService;


    @RequestMapping("/stocktakings")
    public ResponseEntity<?> takeStock(@ApiParam(value = "Stock object that needs to be added to the stocktaking", required = true)
                                       @Valid @RequestBody StocktakingDTO stocktakingDTO
                                        , @RequestHeader("userId") String userId) {

        try {
            Stocktaking stocktaking = new Stocktaking();

            List<StocktakingEntry> allStockTaken = new ArrayList<>();

            int i = 0;

            if (stocktakingDTO == null) {
                throw new BusinessException("Stocktaking cannot be null", HttpStatus.NOT_FOUND);
            }

            User user = userBusinessService.findById(userId);
            if (user == null) {
                throw new BusinessException("User doesn't exist", HttpStatus.NOT_FOUND);
            }

            for (StockDTO stockTakenDTO : stocktakingDTO.getStockItems()) {

                StocktakingEntry stockEntry = new StocktakingEntry();
                stocktaking.addStockCountedByRetailer(stockEntry);

                Product product = new Product();
                product.setId(stockTakenDTO.getProduct().getId());

                stockEntry.setProduct(product);
                stockEntry.setQuantity(stockTakenDTO.getQuantity());


                allStockTaken.add(stockEntry);
            }





            UserStatus userStatus = user.getStatus();
            StockTakingDoneDTO stockTakingDoneDTO;

            if (user.getStatus().getDescription().equals("STOCKTAKE_PENDING")) {
                Retailer retailer = retailerBusinessService.getRetailerByUser(user);

                if(retailer != null) {
                    stocktaking.setRetailer(retailer);
                } else {
                    throw new BusinessException("User doesn't belong to retailer", HttpStatus.UNAUTHORIZED);
                }

                stocktaking.setUser(user);
                stocktaking.setStocktakeDate(stocktakingDTO.getEntryDate());
                stocktaking.setStockCountedByRetailer(allStockTaken);

                Stocktaking stocktakingDone = stockBusinessService.takeStock(stocktaking);

                stocktakingDone.getBalances().booleanValue();
                stockTakingDoneDTO = new StockTakingDoneDTO();
                stocktakingDone.getUser().getStatus();

                User user1 = userBusinessService.findById(stocktaking.getUser().getId());

                stockTakingDoneDTO.setUser(user1);
                List<String> listOfErrorMessages = stocktakingDone.getErrorMessages();
                stockTakingDoneDTO.setErrorMessage(listOfErrorMessages);

            } else {
                throw new BusinessException("The user cannot stocktake if status is: " + user.getStatus().getDescription(), HttpStatus.NOT_ACCEPTABLE);
            }

            return new ResponseEntity<StockTakingDoneDTO>(stockTakingDoneDTO, HttpStatus.OK);

        } catch (BusinessException be) {
            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setErrorMessage(be.getMessage());
            errorDTO.setErrorCode(be.getStatusCode().toString());

            return new ResponseEntity<ErrorDTO>(errorDTO, be.getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<ErrorDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    public ResponseEntity<StocktakingEntryDTO> getStocktakingByDate(@ApiParam(value = "pass an optional search date for looking up inventory") @Valid @RequestParam(value = "date", required = false) String date) {
//            try {
//                return new ResponseEntity<StocktakingEntryDTO>(HttpStatus.NOT_IMPLEMENTED);
//            } catch (Exception e) {
//                log.error("Couldn't serialize response for content type application/json", e);
//                return new ResponseEntity<StocktakingEntryDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//    }
//
//    //TODO: Code me
//    public ResponseEntity<StocktakingEntryDTO> getStocktakingById(@ApiParam(value = "return stocktake by id",required=true) @PathVariable("id") String id) {
//            try {
//                return new ResponseEntity<StocktakingEntryDTO>(HttpStatus.NOT_IMPLEMENTED);
//            } catch (Exception e) {
//                log.error("Couldn't serialize response for content type application/json", e);
//                return new ResponseEntity<StocktakingEntryDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//    }
//
//    //TODO: Code me
//    @Override
//    public ResponseEntity<StocktakingDTO> balanceStockTaking(@Valid StocktakingDTO body) {
//    	// TODO Auto-generated method stub
//    	return null;
//    }

}
