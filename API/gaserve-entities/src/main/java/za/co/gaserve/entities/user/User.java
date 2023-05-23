package za.co.gaserve.entities.user;

import java.util.Date;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;

import za.co.gaserve.entities.Entity;
import za.co.gaserve.entities.lists.UserStatus;

@DynamoDBTable(tableName = "User")

/**
 * 
 * @author sellotseka
 *
 * The id for user is email
 */
public class User extends Entity{

    private String name;
    private String surname;
    private String title;
    private String email;
    private String cellPhonenUmber;

    @Override
    public String toString() {
        return "User{" +
                //"name='" + name + '\'' +
                //", surname='" + surname + '\'' +
                //", title='" + title + '\'' +
                ", email='" + email + '\'' +
                //", cellPhonenUmber='" + cellPhonenUmber + '\'' +
                ", status=" + status +
                //", roles=" + roles +
                //", groups=" + groups +
                '}';
    }

    public User(String email, UserStatus status) {
        this.email = email;
        this.status = status;

    }

    public User() {
    }

    @DynamoDBTyped(DynamoDBAttributeType.M)
    private UserStatus status;


    @DynamoDBTyped(DynamoDBAttributeType.L)
    private List<Role> roles;
    
    @DynamoDBTyped(DynamoDBAttributeType.L)
    private List<Group> groups;

    public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}



//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getSurname() {
//		return surname;
//	}
//
//	public void setSurname(String surname) {
//		this.surname = surname;
//	}
//
//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getCellPhonenUmber() {
//        return cellPhonenUmber;
//    }
//
//    public void setCellPhonenUmber(String cellPhonenUmber) {
//        this.cellPhonenUmber = cellPhonenUmber;
//    }


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

}
