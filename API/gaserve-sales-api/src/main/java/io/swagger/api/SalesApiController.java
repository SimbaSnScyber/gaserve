package io.swagger.api;

import java.util.List;

import javax.validation.Valid;

import co.za.gaserve.business.RetailerBusinessService;
import io.swagger.model.ErrorDTO;
import io.swagger.model.HttpResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.threeten.bp.LocalDate;

import co.za.gaserve.business.SaleBusinessService;
import io.swagger.annotations.ApiParam;
import io.swagger.model.SaleDTO;
import io.swagger.model.SaleItemDTO;
import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.BusinessException;
import za.co.gaserve.entities.lists.PaymentMethod;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.sales.Sale;
import za.co.gaserve.entities.sales.SaleItem;
import za.co.gaserve.entities.stock.Product;
import za.co.gaserve.entities.user.Customer;
import za.co.gaserve.entities.user.User;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-22T18:20:11.403Z")

@Controller
public class SalesApiController implements SalesApi {

    private static final Logger log = LoggerFactory.getLogger(SalesApiController.class);

    @Autowired
    private SaleBusinessService salesBusinessService;

    @Autowired
    private RetailerBusinessService retailerBusinessService;

    @Autowired
    private Dao<Product> productDao;

    @Autowired
    private Dao<User> userDao;

    @Autowired
    private Dao<Customer> customerDao;

    @Autowired
    private Dao<PaymentMethod> paymentMethodDao;

    HttpResponseBody httpResponseBody = new HttpResponseBody();

    @PostMapping("/headerSales")
    public ResponseEntity<HttpResponseBody> addSales(
            @ApiParam(value = "Sale(s) object that nedds to be added to DB", required = true)
            @Valid
            @RequestBody SaleDTO saleDto, @RequestHeader("userId") String headers) {

        try {
            String userId = headers;


            User user = userDao.findById(userId);

            if(retailerBusinessService.getRetailerByUser(user) == null){
                throw new BusinessException("Retailer associated with User "+userDao.findById(userId).getEmail()+" not found.", HttpStatus.PRECONDITION_FAILED);
            }


            Retailer retailer = retailerBusinessService.getRetailerByUser(user);

            if (retailer == null) {
                throw new BusinessException("Retailer not found", HttpStatus.NOT_FOUND);
            }

            if (user ==null ) {
                throw new BusinessException("User not found", HttpStatus.NOT_FOUND);
            }

            Sale sale = new Sale();

            sale.setUser(user);
            sale.setRetailer(retailer);
            sale.setPaymentMethod(paymentMethodDao.findById(saleDto.getPaymentMethod()));

            Customer customer;
            Customer customerCheck = customerDao.findOneByField("cellNumber", saleDto.getConsumer());

            if (customerCheck == null) {
                customer = new Customer(saleDto.getConsumer());
                customerDao.create(customer);
            } else {
                customer = customerCheck;
            }

            if(saleDto.getItems().size() == 0) {
                throw new BusinessException("No stock being sold", HttpStatus.NOT_FOUND);
            } else {
                for (int i = 0; i < saleDto.getItems().size(); i++) {
                    if (productDao.findById(saleDto.getItems().get(i).getProductId()) == null) {
                        throw new BusinessException("Invalid Product : " + saleDto.getItems().get(i).getProductId() + " ", HttpStatus.NOT_FOUND);
                    }
                }
            }

            sale.setVatPercentage(1.15);
            sale.setCustomer(customer);

            List<SaleItemDTO> itemDtos = saleDto.getItems();

            for (SaleItemDTO itemDto : itemDtos) {
                sale.addItem(new SaleItem(productDao.findById(itemDto.getProductId()), itemDto.getQuantity()));
            }

            sale.setPaymentMethod(paymentMethodDao.findById(saleDto.getPaymentMethod()));

            if (sale == null) {
                throw new BusinessException("Sale cannot be null", HttpStatus.NOT_FOUND);
            } else {
                if (user.getStatus().getDescription().equals("ACTIVE")) {
                    salesBusinessService.sell(sale);
                } else {
                    throw new BusinessException("User can't make sale if status is " + user.getStatus().getDescription(),HttpStatus.PRECONDITION_FAILED);
                }
            }

            httpResponseBody.setHttpResponseCode("201");
            httpResponseBody.setHttpResponseMessage("CREATED");

            return new ResponseEntity<HttpResponseBody>(httpResponseBody, HttpStatus.CREATED);

        } catch (BusinessException e) {

            e.printStackTrace();
            ErrorDTO errorDTO = new ErrorDTO();
            return new ResponseEntity(errorDTO.errorMessage(e.getMessage()), e.getStatusCode());

        } catch (Exception e) {

            httpResponseBody.setHttpResponseCode("500");
            httpResponseBody.setHttpResponseMessage("INTERNAL_SERVER_ERROR");

            return new ResponseEntity<HttpResponseBody>(httpResponseBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<SaleDTO>> salesGet(
            @ApiParam(value = "The start date of the sales") @Valid @RequestParam(value = "fromDate", required = false) LocalDate fromDate,
            @ApiParam(value = "The end date of the sales") @Valid @RequestParam(value = "toDate", required = false) LocalDate toDate) {

        try {
            return new ResponseEntity<List<SaleDTO>>(HttpStatus.NOT_IMPLEMENTED);
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<List<SaleDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<SaleDTO> salesIdGet(
            @ApiParam(value = "The id of the sale", required = true) @PathVariable("id") String id) {


        try {
            return new ResponseEntity<SaleDTO>(HttpStatus.NOT_IMPLEMENTED);
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<SaleDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
