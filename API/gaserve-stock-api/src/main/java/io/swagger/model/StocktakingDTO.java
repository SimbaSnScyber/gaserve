package io.swagger.model;

import java.util.Date;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.StockDTO;
import java.util.ArrayList;
import java.util.List;
import org.threeten.bp.LocalDate;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Stocktaking
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-01T17:50:20.640Z")

public class StocktakingDTO   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("stockItems")
  @Valid
  private List<StockDTO> stockItems = null;

  @JsonProperty("entryDate")
  private Date entryDate = null;

  @JsonProperty("userId")
  private String userId = null;

  public StocktakingDTO id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public StocktakingDTO stockItems(List<StockDTO> stockItems) {
    this.stockItems = stockItems;
    return this;
  }

  public StocktakingDTO addStockItemsItem(StockDTO stockItemsItem) {
    if (this.stockItems == null) {
      this.stockItems = new ArrayList<StockDTO>();
    }
    this.stockItems.add(stockItemsItem);
    return this;
  }

  /**
   * Get stockItems
   * @return stockItems
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<StockDTO> getStockItems() {
    return stockItems;
  }

  public void setStockItems(List<StockDTO> stockItems) {
    this.stockItems = stockItems;
  }

  public StocktakingDTO entryDate(Date entryDate) {
    this.entryDate = entryDate;
    return this;
  }

  /**
   * Get entryDate
   * @return entryDate
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Date getEntryDate() {
    return entryDate;
  }

  public void setEntryDate(Date entryDate) {
    this.entryDate = entryDate;
  }

  public StocktakingDTO userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(value = "")


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StocktakingDTO stocktaking = (StocktakingDTO) o;
    return Objects.equals(this.id, stocktaking.id) &&
        Objects.equals(this.stockItems, stocktaking.stockItems) &&
        Objects.equals(this.entryDate, stocktaking.entryDate) &&
        Objects.equals(this.userId, stocktaking.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, stockItems, entryDate, userId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Stocktaking {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    stockItems: ").append(toIndentedString(stockItems)).append("\n");
    sb.append("    entryDate: ").append(toIndentedString(entryDate)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
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
}

