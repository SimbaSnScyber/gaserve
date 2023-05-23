package io.swagger.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.lists.UserStatus;
import za.co.gaserve.entities.regions.Region;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.user.User;

import java.util.Objects;

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-10T14:10:34.174Z")

public class RetailerDTO {

    @Autowired
    private Dao<User> userDao;
    @Autowired
    private Dao<UserStatus> userStatusDao;

    @Autowired
    private Dao<Region> regionDao;

    @Autowired
    private Dao<Retailer> retailerDao;

    @JsonProperty("retailerName")
    private String retailerName = null;

    @JsonProperty("region")
    private String region = null;

    @JsonProperty("userEmail")
    private String userEmail = null;


    /**
     * Get retailerName
     * @return retailerName
     **/
    @ApiModelProperty(value = "")

    public String getRetailerName() {
        return retailerName;
    }

    public void setRetailerName(String retailerName) {
        this.retailerName = retailerName;
    }

    /**
     * Get region
     * @return region
     **/
    @ApiModelProperty(value = "")

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Get userEmail
     * @return userEmail
     **/
    @ApiModelProperty(value = "")
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


    @Override
    public int hashCode() {
        return Objects.hash( retailerName,region,userEmail);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RetailerDTO {\n");


        sb.append("    retailerName: ").append(toIndentedString(retailerName)).append("\n");
        sb.append("    region: ").append(toIndentedString(region)).append("\n");
        sb.append("    userEmail: ").append(toIndentedString(userEmail)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}
