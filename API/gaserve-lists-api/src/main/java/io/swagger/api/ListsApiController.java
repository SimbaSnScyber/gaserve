package io.swagger.api;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.za.gaserve.business.ListBusinessService;
import io.swagger.annotations.ApiParam;
import io.swagger.model.CodeDescriptionDTO;
import io.swagger.model.ProductDTO;
import io.swagger.model.RegionDTO;
import io.swagger.model.StaticListsDTO;
import za.co.gaserve.entities.BusinessException;
import za.co.gaserve.entities.lists.PaymentMethod;
import za.co.gaserve.entities.lists.UserStatus;
import za.co.gaserve.entities.regions.Region;
import za.co.gaserve.entities.stock.Product;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-10T20:50:04.748Z")

@Controller
public class ListsApiController implements ListsApi {

    private static final Logger log = LoggerFactory.getLogger(ListsApiController.class);

    @Autowired
    private ListBusinessService listBusinessService;


    public ResponseEntity<List<CodeDescriptionDTO>> getList(@ApiParam(value = "The listId of the list being searched for", required = true) @PathVariable("id") String id) {
        try {
            List<PaymentMethod> paymentMethods = listBusinessService.getPaymentMethods();
            List<UserStatus> userStatuses = listBusinessService.getUserStatuses();

            List<CodeDescriptionDTO> dtos = null;

            if ("PAYMENT_METHODS".equals(id)) {
                dtos = CodeDescriptionDTO.fromPaymentMethods(paymentMethods);
            } else if ("USER_STATUSES".equals(id)) {
                dtos = CodeDescriptionDTO.fromUserStatuses(userStatuses);
            } else {
                return new ResponseEntity<List<CodeDescriptionDTO>>(dtos, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<List<CodeDescriptionDTO>>(dtos, HttpStatus.OK);
        } catch (BusinessException e) {
            log.error("Business Error", e);
            return new ResponseEntity<List<CodeDescriptionDTO>>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Unexpected Error", e);
            return new ResponseEntity<List<CodeDescriptionDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<StaticListsDTO> getLists() {
        try {

            List<PaymentMethod> paymentMethods = listBusinessService.getPaymentMethods();
            List<Product> products = listBusinessService.getProducts();
            List<Region> regions = listBusinessService.getRegions();
            List<UserStatus> userStatuses = listBusinessService.getUserStatuses();

            List<CodeDescriptionDTO> paymentMethodDtos = CodeDescriptionDTO.fromPaymentMethods(paymentMethods);
            List<ProductDTO> productDtos = new ArrayList<ProductDTO>();
            List<RegionDTO> regionDtos = new ArrayList<RegionDTO>();
            List<CodeDescriptionDTO> userStatusDtos = CodeDescriptionDTO.fromUserStatuses(userStatuses);

            for (Product product : products) {
                productDtos.add(new ProductDTO(product));
            }

            for (Region region : regions) {
                regionDtos.add(new RegionDTO(region));
            }

            StaticListsDTO staticLists =
                    new StaticListsDTO(
                            paymentMethodDtos,
                            productDtos,
                            regionDtos,
                            userStatusDtos
                    );

            return new ResponseEntity<StaticListsDTO>(staticLists, HttpStatus.OK);
        } catch (BusinessException e) {
            log.error("Business Error", e);
            return new ResponseEntity<StaticListsDTO>(e.getStatusCode());
        } catch (Exception e) {
            log.error("Unexpected Error", e);
            return new ResponseEntity<StaticListsDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void setListBusinessService(ListBusinessService listBusinessService) {
        this.listBusinessService = listBusinessService;
    }
}
