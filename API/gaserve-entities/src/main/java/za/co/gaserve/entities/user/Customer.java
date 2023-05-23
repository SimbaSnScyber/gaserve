package za.co.gaserve.entities.user;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import za.co.gaserve.entities.Entity;
import za.co.gaserve.entities.regions.Retailer;

/**
 * 
 * @author sellotseka
 *
 * The ids are cell numbers of customers
 */
@DynamoDBTable(tableName = "Customer")
public class Customer extends Entity{
    private String name;//If we have it.
    private String email;//If we have it.
    private String cellNumber;//If we have it.
    private String secondaryNumber;//If we have it.
    private String surname;//If we have it.
    private String title;//If we have it.
    private Date dateOfBirth;//If we have it.

	public Customer(String cellNumber) {
		this.cellNumber = cellNumber;
	}

	public Customer() {
	}

	@DynamoDBHashKey(attributeName="id")
    public String getId()
    {
       return id;
    }
  
    @Override
    public Boolean getActive() {
    	return active;
    }
    
    @Override
    public Date getCreatedDate() {
    	return createdDate;
    }
    
    @Override
    public Date getLastUpdate() {
    	return lastUpdate;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellNumber() {
		return cellNumber;
	}

	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}

	public String getSecondaryNumber() {
		return secondaryNumber;
	}

	public void setSecondaryNumber(String secondaryNumber) {
		this.secondaryNumber = secondaryNumber;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
