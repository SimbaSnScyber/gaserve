package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * User
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-10T20:50:13.667Z")

public class User   {
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("email")
  private String email = null;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    UNBALANCING("UNBALANCING"),
    
    ACTIVE("ACTIVE"),
    
    DISABLED("DISABLED"),
    
    REGISTERED("REGISTERED");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("status")
  private StatusEnum status = null;

  @JsonProperty("region")
  private String region = null;

  @JsonProperty("retailer")
  private String retailer = null;

  @JsonProperty("active")
  private Boolean active = null;

  @JsonProperty("dateCreated")
  private OffsetDateTime dateCreated = null;

  @JsonProperty("dateUpdated")
  private OffsetDateTime dateUpdated = null;

  @JsonProperty("updatedBy")
  private String updatedBy = null;

  @JsonProperty("roles")
  @Valid
  private List<Item> roles = null;

  @JsonProperty("groups")
  @Valid
  private List<Item> groups = null;

  public User id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public User name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public User status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public User region(String region) {
    this.region = region;
    return this;
  }

  /**
   * Get region
   * @return region
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public User retailer(String retailer) {
    this.retailer = retailer;
    return this;
  }

  /**
   * Get retailer
   * @return retailer
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getRetailer() {
    return retailer;
  }

  public void setRetailer(String retailer) {
    this.retailer = retailer;
  }

  public User active(Boolean active) {
    this.active = active;
    return this;
  }

  /**
   * Get active
   * @return active
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Boolean isActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public User dateCreated(OffsetDateTime dateCreated) {
    this.dateCreated = dateCreated;
    return this;
  }

  /**
   * Get dateCreated
   * @return dateCreated
  **/
  @ApiModelProperty(example = "2016-08-29T09:12:33.001Z", required = true, value = "")
  @NotNull

  @Valid

  public OffsetDateTime getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(OffsetDateTime dateCreated) {
    this.dateCreated = dateCreated;
  }

  public User dateUpdated(OffsetDateTime dateUpdated) {
    this.dateUpdated = dateUpdated;
    return this;
  }

  /**
   * Get dateUpdated
   * @return dateUpdated
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public OffsetDateTime getDateUpdated() {
    return dateUpdated;
  }

  public void setDateUpdated(OffsetDateTime dateUpdated) {
    this.dateUpdated = dateUpdated;
  }

  public User updatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
    return this;
  }

  /**
   * Get updatedBy
   * @return updatedBy
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  public User roles(List<Item> roles) {
    this.roles = roles;
    return this;
  }

  public User addRolesItem(Item rolesItem) {
    if (this.roles == null) {
      this.roles = new ArrayList<Item>();
    }
    this.roles.add(rolesItem);
    return this;
  }

  /**
   * Get roles
   * @return roles
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Item> getRoles() {
    return roles;
  }

  public void setRoles(List<Item> roles) {
    this.roles = roles;
  }

  public User groups(List<Item> groups) {
    this.groups = groups;
    return this;
  }

  public User addGroupsItem(Item groupsItem) {
    if (this.groups == null) {
      this.groups = new ArrayList<Item>();
    }
    this.groups.add(groupsItem);
    return this;
  }

  /**
   * Get groups
   * @return groups
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Item> getGroups() {
    return groups;
  }

  public void setGroups(List<Item> groups) {
    this.groups = groups;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.id, user.id) &&
        Objects.equals(this.name, user.name) &&
        Objects.equals(this.email, user.email) &&
        Objects.equals(this.status, user.status) &&
        Objects.equals(this.region, user.region) &&
        Objects.equals(this.retailer, user.retailer) &&
        Objects.equals(this.active, user.active) &&
        Objects.equals(this.dateCreated, user.dateCreated) &&
        Objects.equals(this.dateUpdated, user.dateUpdated) &&
        Objects.equals(this.updatedBy, user.updatedBy) &&
        Objects.equals(this.roles, user.roles) &&
        Objects.equals(this.groups, user.groups);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, email, status, region, retailer, active, dateCreated, dateUpdated, updatedBy, roles, groups);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    region: ").append(toIndentedString(region)).append("\n");
    sb.append("    retailer: ").append(toIndentedString(retailer)).append("\n");
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
    sb.append("    dateCreated: ").append(toIndentedString(dateCreated)).append("\n");
    sb.append("    dateUpdated: ").append(toIndentedString(dateUpdated)).append("\n");
    sb.append("    updatedBy: ").append(toIndentedString(updatedBy)).append("\n");
    sb.append("    roles: ").append(toIndentedString(roles)).append("\n");
    sb.append("    groups: ").append(toIndentedString(groups)).append("\n");
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

