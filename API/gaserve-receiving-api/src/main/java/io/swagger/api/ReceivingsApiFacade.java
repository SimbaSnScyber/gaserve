package io.swagger.api;


import co.za.gaserve.business.ReceivingBusinessService;
import co.za.gaserve.business.RetailerBusinessService;
import co.za.gaserve.business.StockBusinessService;

import com.amazonaws.services.dynamodbv2.xspec.B;
import io.swagger.model.ErrorDTO;

import io.swagger.model.HttpResponseBody;
import io.swagger.model.ReceivingsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.BusinessException;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.stock.Product;
import za.co.gaserve.entities.stock.ReceivedItem;
import za.co.gaserve.entities.stock.Receiving;
import za.co.gaserve.entities.user.User;

import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-12T18:18:01.135Z")

@Service
@Controller
public class ReceivingsApiFacade {


    @Autowired
    private Dao<Product> productDao;

    @Autowired
    private Dao<User> userDao;

    @Autowired
    private RetailerBusinessService retailerBusinessService;

    @Autowired
    private StockBusinessService stockBusinessService;

    @Autowired
    private ReceivingBusinessService receivingBusinessService;

    HttpResponseBody httpResponseBody = new HttpResponseBody();
    // ErrorDTO httpResponseBody = new ErrorDTO();

    @PostMapping("/receive")
    public ResponseEntity<?> doReceiving(@RequestBody ReceivingsDTO receivingsDTO, @RequestHeader("userId") String user) {
        System.out.println("SERVICE RECEIVING");
        try {
            //Inorder to do a proper receiving record, we need, the product, and the retailer where the transaction is happening---we find the retailer by the user

            User receiver = userDao.findById(user);
            if (!receiver.getStatus().getDescription().equals("ACTIVE")) {
                throw new BusinessException("User can't do receiving if status is: " + receiver.getStatus().getDescription(), HttpStatus.NOT_ACCEPTABLE);
            }

            Retailer receivingRetailer = retailerBusinessService.getRetailerByUser(receiver);

            if (!receiver.equals(null)) {

                if (receivingRetailer != null) {
                    Receiving newStock = new Receiving();
                    List<Receiving> receivings = new ArrayList<>();
                    List<ReceivedItem> received = new ArrayList<>();
                    ReceivedItem receivedItem = new ReceivedItem();
                    Product productReceived = new Product();


                    for (int items = 0; items < receivingsDTO.getReceivedItemList().size(); items++) {

                        productReceived = productDao.findById(receivingsDTO.getReceivedItemList().get(items).getProduct().getId());
                        double quantity = receivingsDTO.getReceivedItemList().get(items).getQuantity();
                        if (productReceived == null) {
                            throw new BusinessException("Product does not exist", HttpStatus.NOT_FOUND);
                        } else if (quantity <= 0) {
                            throw new BusinessException("Quantity should be greater than Zero. " + receivingsDTO.getReceivedItemList().get(items).getProduct().getId() + " Quantity is " + quantity + "", HttpStatus.PRECONDITION_FAILED);
                        }

                        receivedItem.setProduct(productReceived);
                        receivedItem.setQuantity(receivingsDTO.getReceivedItemList().get(items).getQuantity());
                        received.add(receivedItem);
                    }
                    newStock.setItems(received);
                    receivingBusinessService.receive(newStock);
                    for (int items = 0; items < received.size(); items++) {

                        stockBusinessService.receiveItem(received.get(items), receivingRetailer);
                    }

                    httpResponseBody.setHttpResponseCode("200");
                    httpResponseBody.setHttpResponseMessage("success");
                    return new ResponseEntity(httpResponseBody, HttpStatus.OK);
                } else {
                    throw new BusinessException("Retailer for "+userDao.findById(user).getEmail()+" not found", HttpStatus.NOT_FOUND);
                }
            }
            else {
                throw new BusinessException("User not found", HttpStatus.NOT_FOUND);
            }

        } catch (BusinessException be) {
            be.printStackTrace();
            ErrorDTO errorDTO = new ErrorDTO();
            return new ResponseEntity(errorDTO.errorMessage(be.getMessage()), be.getStatusCode());

        } catch (Exception e) {
            e.printStackTrace();
            httpResponseBody.setHttpResponseCode("500");
            httpResponseBody.setHttpResponseMessage("Something went wrong");
            return new ResponseEntity(httpResponseBody, HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }


}
