package io.swagger.api;


import io.swagger.annotations.*;
import io.swagger.model.ErrorDTO;
import io.swagger.model.RetailerDTO;
import io.swagger.model.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-22T18:20:11.403Z")

@Api(value="admin", description = "admin api")

public interface AdminApi {

    @ApiOperation(value= "create new user" , nickname = "CreateUser", notes="creates new user",response = List.class, responseContainer = "List", tags={ "lists", })
    @ApiResponses( value = {
            @ApiResponse(code=201, message = "CREATED"),
            @ApiResponse(code=412, message = "user already exist", response = ErrorDTO.class),
            @ApiResponse(code=500, message = "Unexpected Error", response = ErrorDTO.class)})
    @RequestMapping(value = "/admin/createuser/",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<?> createUser(
            @ApiParam(value = "user object that nedds to be added to DB", required = true)
            @Valid @RequestBody UserDTO userDTO);



    @ApiOperation(value= "update user status" , nickname = "UpdateUserStatus", notes="updates user status of an already existing user",response = List.class, responseContainer = "List", tags={ "lists", })
    @ApiResponses( value = {
            @ApiResponse(code=200, message = "UPDATED"),
            @ApiResponse(code=401, message = "unable to update user status", response = ErrorDTO.class),
            @ApiResponse(code=500, message = "Unexpected Error", response = ErrorDTO.class)})
    @RequestMapping(value = "/admin/updatestatus/",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<?> updateUserStatus(
            @ApiParam(value = "user object that nedds to be added to DB", required = true)
            @Valid
            @RequestBody UserDTO userDTO);


    @ApiOperation(value= "create retailer" , nickname = "CreateRetailer", notes="create new retailer",response = List.class, responseContainer = "List", tags={ "lists", })
    @ApiResponses( value = {
            @ApiResponse(code=201, message = "CREATED"),
            @ApiResponse(code=412, message = "Retailer already Exist", response = ErrorDTO.class),
            @ApiResponse(code=500, message = "Unexpected Error", response = ErrorDTO.class)})
    @RequestMapping(value = "/admin/newretailer/",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<?> createRetailer(
            @ApiParam(value = "user object that nedds to be added to DB", required = true)
            @Valid
            @RequestBody RetailerDTO retailerDTO);

    @ApiOperation(value= "Update retail manger" , nickname = "updateRetailerManager", notes="allocating a new manager to a retailer",response = List.class, responseContainer = "List", tags={ "lists", })
    @ApiResponses( value = {
            @ApiResponse(code=200, message = "UPDATED"),
            @ApiResponse(code=412, message = "unable to update retail manager", response = ErrorDTO.class),
            @ApiResponse(code=500, message = "Unexpected Error", response = ErrorDTO.class)})
    @RequestMapping(value = "/admin/updateretailermanager/",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<?> updateRetailerManager(
            @ApiParam(value = "user object that nedds to be added to DB", required = true)
            @Valid
            @RequestBody RetailerDTO retailerDTO);

}
