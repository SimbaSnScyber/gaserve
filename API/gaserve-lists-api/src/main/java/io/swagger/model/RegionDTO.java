package io.swagger.model;

import java.util.ArrayList;
import java.util.List;

import io.swagger.api.ListsApiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import za.co.gaserve.entities.BusinessException;
import za.co.gaserve.entities.regions.Region;
import za.co.gaserve.entities.regions.Retailer;

/**
 * Error
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-10T20:50:04.748Z")

public class RegionDTO   {

	private static final Logger log = LoggerFactory.getLogger(ListsApiController.class);

	public RegionDTO(){		
	}
	
	public RegionDTO(Region region){
		
		this.id = region.getId();
		this.name = region.getName();

		List<CodeDescriptionDTO> dtos = null;

		try{

			for(Retailer retailer:region.getRetailers()){
				addRetailer(retailer); }

		}catch(Exception e){
			log.error("Unexpected Error", e);
			System.out.println(" Not Found");//personalise error
		}


	}
	
	
	public void addRetailer(Retailer retailer){
		if(retailers == null){
			retailers = new ArrayList<CodeDescriptionDTO>();
		}
		retailers.add(new CodeDescriptionDTO(retailer.getId(),retailer.getName()));
	}
	
	private String name;
	private String id;	
	private List<CodeDescriptionDTO> retailers;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<CodeDescriptionDTO> getRetailers() {
		return retailers;
	}

	public void setRetailers(List<CodeDescriptionDTO> retailers) {
		this.retailers = retailers;
	}



}



