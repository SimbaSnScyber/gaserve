package io.swagger.model;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Sales
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-12T18:18:01.135Z")

public class LockedRetailerDTO {
	
	public LockedRetailerDTO(){}
	
	public LockedRetailerDTO(String userId,String retailerId){
		this.userId = userId;
		this.retailerId = retailerId;
	}
	
	@JsonProperty("userId")
	private String userId = null;

	@JsonProperty("retailerId")
	private String retailerId = null;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}
	
}
