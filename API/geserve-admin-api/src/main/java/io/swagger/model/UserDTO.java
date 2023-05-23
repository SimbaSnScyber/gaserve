package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.lists.UserStatus;

import java.util.List;
import java.util.Objects;

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-10T14:10:34.174Z")



public class UserDTO {

    @Autowired
    private Dao<UserStatus> userStatusDao;

    @JsonProperty("userEmail")
    private String userEmail = null;

    @JsonProperty("userStatus")
    private String userStatus;

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

    /**
     * Get userStatus
     * @return userStatus
     **/

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {

        this.userStatus = userStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash( userEmail,userStatus);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UserDTO {\n");


        sb.append("    userEmail: ").append(toIndentedString(userEmail)).append("\n");
        sb.append("    userStatus: ").append(toIndentedString(userStatus)).append("\n");
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
