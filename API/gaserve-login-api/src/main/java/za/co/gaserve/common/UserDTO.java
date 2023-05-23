package za.co.gaserve.common;

import java.util.Date;
import java.util.Objects;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import za.co.gaserve.entities.user.User;

/**
 * UserDTO
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-12T10:15:47.525Z")

public class UserDTO {

  public UserDTO() {
  }

  public UserDTO(User user) {
    this.id = user.getId();
    this.active = user.getActive();
    this.email = user.getEmail();
    this.dateCreated = user.getCreatedDate();
  }


  public UserDTO(String id, Boolean active, Date dateCreated, Date dateUpdated, String updateBy, String email, Integer retryCount, Boolean userLocked) {
    this.id = id;
    this.active = active;
    this.dateCreated = dateCreated;
    this.email = email;
    this.userLocked = userLocked;
  }

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("active")
  private Boolean active = null;

  @JsonProperty("dateCreated")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
  private Date dateCreated = null;


  @JsonProperty("email")
  private String email = null;

  @JsonProperty
  private String userStatus;

  public String getUserStatus() {
    return userStatus;
  }

  public void setUserStatus(String userStatus) {
    this.userStatus = userStatus;
  }

  @JsonProperty("userLocked")
  private Boolean userLocked = null;

  @JsonProperty("stockTakingDone")
  private Boolean stockTakingDone = null;

  public UserDTO id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The user ID.
   * @return id
  **/
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public UserDTO active(Boolean active) {
    this.active = active;
    return this;
  }

  /**
   * The status of the logged In user
   * @return active
  **/

  public Boolean isActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public UserDTO dateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
    return this;
  }

  /**
   * Data the user was created
   * @return dateCreated
  **/
  @Valid

  public Date getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }


  /**
   * The email associated with the user
   * @return email
  **/

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public UserDTO userLocked(Boolean userLocked) {
    this.userLocked = userLocked;
    return this;
  }

  /**
   * Get userLocked
   * @return userLocked
  **/
  public Boolean isUserLocked() {
    return userLocked;
  }

  public void setUserLocked(Boolean userLocked) {
    this.userLocked = userLocked;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserDTO userDTO = (UserDTO) o;
    return Objects.equals(this.id, userDTO.id) &&
        Objects.equals(this.active, userDTO.active) &&
        Objects.equals(this.dateCreated, userDTO.dateCreated) && Objects.equals(this.email, userDTO.email) &&
        Objects.equals(this.userLocked, userDTO.userLocked);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, active, dateCreated, email, userLocked);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserDTO {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
    sb.append("    dateCreated: ").append(toIndentedString(dateCreated)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    userLocked: ").append(toIndentedString(userLocked)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  public Boolean getStockTakingDone() {
    return stockTakingDone;
  }

  public void setStockTakingDone(Boolean stockTakingDone) {
    this.stockTakingDone = stockTakingDone;
  }
}

