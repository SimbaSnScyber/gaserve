package za.co.gaserve.entities.user;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import za.co.gaserve.entities.Entity;

@DynamoDBTable(tableName = "Login")
public class Login extends Entity {

	@DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.M)
	private User user;


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private String token;
	private String ip;
	private String mac;
	private String deviceType;
	private Boolean successful;
	private String failureReason;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public Boolean getSuccessful() {
		return successful;
	}

	public void setSuccessful(Boolean successful) {
		this.successful = successful;
	}

	public String getFailureReason() {
		return failureReason;
	}

	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}

	@DynamoDBHashKey(attributeName = "id")
	public String getId() {
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