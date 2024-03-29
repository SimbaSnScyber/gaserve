/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.ErrorDTO;
import io.swagger.model.StocktakingDTO;
import io.swagger.model.StocktakingDTO;
import za.co.gaserve.entities.stock.Stocktaking;
import za.co.gaserve.entities.stock.StocktakingEntry;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-10T20:38:27.309Z")

@Api(value = "stocktakings", description = "the stocktakings API")
public interface StockApi {

    @ApiOperation(value = "Balnce an unbalancing stock take", nickname = "addStocktakeQuantity", notes = "", response = ErrorDTO.class, tags={ "stocktaking", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Successfully created"),
        @ApiResponse(code = 400, message = "Invalid Stocktake", response = ErrorDTO.class),
        @ApiResponse(code = 409, message = "Invalid input format", response = ErrorDTO.class),
        @ApiResponse(code = 500, message = "Unexpected error", response = ErrorDTO.class) })
    @RequestMapping(value = "/stocktakings/balance",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Stocktaking> balanceStockTaking(@ApiParam(value = "Stock object that needs to be added to the stocktaking" ,required=true )  @Valid @RequestBody StocktakingDTO body);

    @ApiOperation(value = "Add quantity on hand amount after manual stocktake", nickname = "addStocktakeQuantity", notes = "", response = ErrorDTO.class, tags={ "stocktaking", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Successfully created"),
        @ApiResponse(code = 400, message = "Invalid Stocktake", response = ErrorDTO.class),
        @ApiResponse(code = 409, message = "Invalid input format", response = ErrorDTO.class),
        @ApiResponse(code = 500, message = "Unexpected error", response = ErrorDTO.class) })
    @RequestMapping(value = "/stocktakings",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Stocktaking> takeStock(@ApiParam(value = "Stock object that needs to be added to the stocktaking" ,required=true )  @Valid @RequestBody StocktakingDTO body);


    @ApiOperation(value = "Returns all stocktake entries", nickname = "getStocktaking", notes = "Returns a map of all stocktake entries", response = ErrorDTO.class, tags={ "stocktaking", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Successfully created", response = ErrorDTO.class),
        @ApiResponse(code = 400, message = "Invalid Stocktake", response = ErrorDTO.class),
        @ApiResponse(code = 409, message = "Invalid input format", response = ErrorDTO.class),
        @ApiResponse(code = 500, message = "Unexpected error", response = ErrorDTO.class) })
    @RequestMapping(value = "/stocktakings",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Stocktaking> getStocktakingByDate(@ApiParam(value = "pass an optional search date for looking up inventory") @Valid @RequestParam(value = "date", required = false) String date);


    @ApiOperation(value = "Retrieve a stocktake by id", nickname = "getStocktakingById", notes = "Retrieve all stocktakes using id", response = Stocktaking.class, tags={ "stocktaking", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful operation", response = StocktakingDTO.class),
        @ApiResponse(code = 400, message = "Invalid input format supplied", response = ErrorDTO.class),
        @ApiResponse(code = 404, message = "Invalid Id supplied", response = ErrorDTO.class),
        @ApiResponse(code = 500, message = "Unexpected error", response = ErrorDTO.class) })
    @RequestMapping(value = "/stocktakings/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<StocktakingEntry> getStocktakingById(@ApiParam(value = "return stocktake by id",required=true) @PathVariable("id") String id);

}
