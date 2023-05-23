package io.swagger.api;

import co.za.gaserve.business.AdminBusinessService;
import io.swagger.annotations.ApiParam;
import io.swagger.model.ErrorDTO;
import io.swagger.model.HttpResponseBody;
import io.swagger.model.RetailerDTO;
import io.swagger.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.BusinessException;
import za.co.gaserve.entities.lists.UserStatus;
import za.co.gaserve.entities.regions.Region;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.user.User;

import javax.validation.Valid;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-10T20:50:04.748Z")

@Controller
public class AdminApiController implements AdminApi {

    private static final Logger log = LoggerFactory.getLogger(AdminApiController.class);

    @Autowired
    private AdminBusinessService adminBusinessService;

    @Autowired
    private Dao<User> userDao;
    @Autowired
    private Dao<UserStatus> userStatusDao;

    @Autowired
    private Dao<Region> regionDao;

    @Autowired
    private Dao<Retailer> retailerDao;

    HttpResponseBody httpResponseBody = new HttpResponseBody();

    public ResponseEntity<?> createUser(
            @ApiParam(value = "Retailer object that nedds to be added to DB", required = true)
            @Valid
            @RequestBody UserDTO userDTO){
        try {

            if(userDao.findOneByField("email",userDTO.getUserEmail())==null){
                if(userStatusDao.findOneByField("id",userDTO.getUserStatus())==null){
                    throw new BusinessException("invalid USER STATUS ."+userDTO.getUserStatus()+" is an invalid user status ",HttpStatus.PRECONDITION_FAILED);
                }else{
                userDao.create(new User(userDTO.getUserEmail(),userStatusDao.findById(userDTO.getUserStatus())));
                httpResponseBody.setHttpResponseCode("201");
                httpResponseBody.setHttpResponseMessage("CREATED");}
            }else{
                throw new BusinessException("User already exist",HttpStatus.PRECONDITION_FAILED);
            }

            return new ResponseEntity<HttpResponseBody>(httpResponseBody, HttpStatus.CREATED);

        }catch (BusinessException e){
            e.printStackTrace();
            ErrorDTO errorDTO = new ErrorDTO();
            return new ResponseEntity(errorDTO.errorMessage(e.getMessage()), e.getStatusCode());

        }catch (Exception e){
            httpResponseBody.setHttpResponseCode("500");
            httpResponseBody.setHttpResponseMessage("INTERNAL_SERVER_ERROR");

            return new ResponseEntity<HttpResponseBody>(httpResponseBody, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    public ResponseEntity<?> updateUserStatus(
            @ApiParam(value = "Retailer object that nedds to be added to DB", required = true)
            @Valid
            @RequestBody UserDTO userDTO){

        try{
            if(userDao.findOneByField("email",userDTO.getUserEmail())==null){
                throw new BusinessException("User "+userDTO.getUserEmail()+" does not exist. Can not update status for non existing user",HttpStatus.PRECONDITION_FAILED);
            }

            else if(userStatusDao.findOneByField("id",userDTO.getUserStatus())==null){
                throw new BusinessException("invalid USER STATUS ."+userDTO.getUserStatus()+" is an invalid user status ",HttpStatus.PRECONDITION_FAILED);
            }

            else{
            User user = userDao.findByField("email",userDTO.getUserEmail()).get(0);

            user.setStatus(userStatusDao.findById(userDTO.getUserStatus()));
            userDao.update(user);
            httpResponseBody.setHttpResponseCode("200");
            httpResponseBody.setHttpResponseMessage("UPDATED");
            }

        return new ResponseEntity<HttpResponseBody>(httpResponseBody, HttpStatus.OK);
        }catch (BusinessException e){
            e.printStackTrace();
            ErrorDTO errorDTO = new ErrorDTO();
            return new ResponseEntity(errorDTO.errorMessage(e.getMessage()), e.getStatusCode());

        }catch (Exception e){
            httpResponseBody.setHttpResponseCode("500");
            httpResponseBody.setHttpResponseMessage("INTERNAL_SERVER_ERROR");

            return new ResponseEntity<HttpResponseBody>(httpResponseBody, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    public ResponseEntity<?> createRetailer(
            @ApiParam(value = "Retailer object that nedds to be added to DB", required = true)
            @Valid
            @RequestBody RetailerDTO retailerDTO){


        try{


        if(retailerDao.findOneByField("retailerName",retailerDTO.getRetailerName())==null){
            if(userDao.findByField("email",retailerDTO.getUserEmail())==null){
                throw new BusinessException("invalid USER ."+retailerDTO.getUserEmail()+" is an invalid user.",HttpStatus.PRECONDITION_FAILED);
            }
            else{
                retailerDao.create(new Retailer(retailerDTO.getRetailerName(), userDao.findByField("email", retailerDTO.getUserEmail()).get(0)));

                List<Retailer> retailers = retailerDao.findAll();

                Region region = regionDao.findById(retailerDTO.getRegion());
                region.setRetailers(retailers);
                regionDao.update(region);

                httpResponseBody.setHttpResponseCode("200");
                httpResponseBody.setHttpResponseMessage("CREATED");}
        }else{
            throw new BusinessException("Retailer "+retailerDTO.getRetailerName()+" already exist.",HttpStatus.PRECONDITION_FAILED);
        }

        return new ResponseEntity<HttpResponseBody>(httpResponseBody, HttpStatus.OK);

        }catch (BusinessException e){
            e.printStackTrace();
            ErrorDTO errorDTO = new ErrorDTO();
            return new ResponseEntity(errorDTO.errorMessage(e.getMessage()), e.getStatusCode());

        }catch (Exception e){
            httpResponseBody.setHttpResponseCode("500");
            httpResponseBody.setHttpResponseMessage("INTERNAL_SERVER_ERROR");

            return new ResponseEntity<HttpResponseBody>(httpResponseBody, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    public ResponseEntity<?> updateRetailerManager(
            @ApiParam(value = "Retailer object that nedds to be added to DB", required = true)
            @Valid
            @RequestBody RetailerDTO retailerDTO){


        try{


            if(retailerDao.findOneByField("retailerName",retailerDTO.getRetailerName())==null){
                throw new BusinessException("Retailer "+retailerDTO.getRetailerName()+" does not exist. Cannot update manager for non existing retailer",HttpStatus.PRECONDITION_FAILED);
            }else{
                Retailer retailer = retailerDao.findOneByField("retailerName",retailerDTO.getRetailerName());
                retailer.setManager(userDao.findByField("email", retailerDTO.getUserEmail()).get(0));
                retailerDao.update(retailer);

                List<Retailer> retailers = retailerDao.findAll();
                Region region = regionDao.findById(retailerDTO.getRegion());
                region.setRetailers(retailers);
                regionDao.update(region);

                httpResponseBody.setHttpResponseCode("200");
                httpResponseBody.setHttpResponseMessage("UPDATED");
            }

            return new ResponseEntity<HttpResponseBody>(httpResponseBody, HttpStatus.OK);

        }catch (BusinessException e){
            e.printStackTrace();
            ErrorDTO errorDTO = new ErrorDTO();
            return new ResponseEntity(errorDTO.errorMessage(e.getMessage()), e.getStatusCode());

        }catch (Exception e){
            httpResponseBody.setHttpResponseCode("500");
            httpResponseBody.setHttpResponseMessage("INTERNAL_SERVER_ERROR");

            return new ResponseEntity<HttpResponseBody>(httpResponseBody, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
