package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import za.co.gaserve.entities.lists.IdDescription;
import za.co.gaserve.entities.lists.PaymentMethod;
import za.co.gaserve.entities.lists.UserStatus;


public class CodeDescriptionDTO   {
  @JsonProperty("code")
  private String code = null;

  @JsonProperty("description")
  private String description = null;

  public CodeDescriptionDTO() {}

  public CodeDescriptionDTO (String description, String code) {
    this.code = code;
    this.description = description;
  }
  
 

  public static List<CodeDescriptionDTO> fromPaymentMethods(List<PaymentMethod> idDescriptions){
	  List<CodeDescriptionDTO> codes = new ArrayList<CodeDescriptionDTO>();
	  
	  for(IdDescription idName : idDescriptions){
		  codes.add(new CodeDescriptionDTO(idName.getId(),idName.getDescription()));
	  }
	  return codes;
  }

  public static List<CodeDescriptionDTO> fromUserStatuses(List<UserStatus> idDescriptions){
	  List<CodeDescriptionDTO> codes = new ArrayList<CodeDescriptionDTO>();
	  
	  for(IdDescription idName : idDescriptions){
		  codes.add(new CodeDescriptionDTO(idName.getId(),idName.getDescription()));
	  }
	  return codes;
  }

  /**
   * Get code
   * @return code
  **/
  @ApiModelProperty(value = "")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CodeDescriptionDTO codeDescription = (CodeDescriptionDTO) o;
    return Objects.equals(this.code, codeDescription.code) &&
        Objects.equals(this.description, codeDescription.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CodeDescription {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

