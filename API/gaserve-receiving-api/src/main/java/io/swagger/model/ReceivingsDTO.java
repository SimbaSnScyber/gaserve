package io.swagger.model;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.stock.ReceivedItem;
import za.co.gaserve.entities.stock.Receiving;

import javax.validation.constraints.*;

/**
 * Recieving
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-10T18:26:17.750Z")

public class ReceivingsDTO {

  @JsonProperty("receivedItemList")
  private List<ReceivedItem> receivedItemList = null;

  @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.M)
  private Retailer retailer;
  public ReceivingsDTO id(String id) {

    return this;
  }

  public List<ReceivedItem> getReceivedItemList() {
    return receivedItemList;
  }

  public void setReceivedItemList(List<ReceivedItem> receivedItemList) {
    this.receivedItemList = receivedItemList;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull



  public ReceivingsDTO recievingDTO(List<ReceivedItem>receivings){
    this.receivedItemList = receivings;
    return this;
  }

  /**
   * Get productId
   * @return productId
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull



  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Recieving {\n");
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

