package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SaleItem
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-10-11T16:50:34.174Z")

public class SaleItemDTO   {
  @JsonProperty("productId")
  private String productId = null;

  @JsonProperty("quantity")
  private Integer quantity = null;

  @JsonProperty("price")
  private Double price = null;

  @JsonProperty("saleDate")
  private OffsetDateTime saleDate = null;

  public SaleItemDTO productId(String productId) {
    this.productId = productId;
    return this;
  }

  /**
   * Get productId
   * @return productId
   **/
  @ApiModelProperty(value = "")


  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public SaleItemDTO quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Get quantity
   * @return quantity
   **/
  @ApiModelProperty(value = "")


  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public SaleItemDTO price(Double price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
   **/
  @ApiModelProperty(value = "")


  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public SaleItemDTO saleDate(OffsetDateTime saleDate) {
    this.saleDate = saleDate;
    return this;
  }

  /**
   * Get saleDate
   * @return saleDate
   **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getSaleDate() {
    return saleDate;
  }

  public void setSaleDate(OffsetDateTime saleDate) {
    this.saleDate = saleDate;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SaleItemDTO saleItem = (SaleItemDTO) o;
    return Objects.equals(this.productId, saleItem.productId) &&
            Objects.equals(this.quantity, saleItem.quantity) &&
            Objects.equals(this.price, saleItem.price) &&
            Objects.equals(this.saleDate, saleItem.saleDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId, quantity, price, saleDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SaleItem {\n");

    sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    saleDate: ").append(toIndentedString(saleDate)).append("\n");
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

