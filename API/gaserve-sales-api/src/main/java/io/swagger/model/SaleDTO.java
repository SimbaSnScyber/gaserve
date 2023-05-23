package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.SaleItemDTO;
import java.util.ArrayList;
import java.util.List;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SaleDTO
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-10-11T16:50:34.174Z")

public class SaleDTO   {


  @JsonProperty("user")
  private String user = null;

  @JsonProperty("consumer")
  private String consumer = null;

  @JsonProperty("items")
  @Valid
  private List<SaleItemDTO> items = null;

  @JsonProperty("totalExVat")
  private Double totalExVat = null;

  @JsonProperty("totalInclVat")
  private Double totalInclVat = null;

  @JsonProperty("vat")
  private Double vat = null;

  @JsonProperty("price")
  private Double price = null;

  @JsonProperty("vatRate")
  private Double vatRate = null;

  @JsonProperty("paymentMethod")
  private String paymentMethod = null;



  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  /**
   * Get user
   * @return user
   **/
  @ApiModelProperty(value = "")


  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public SaleDTO consumer(String consumer) {
    this.consumer = consumer;
    return this;
  }

  /**
   * Get consumer
   * @return consumer
   **/
  @ApiModelProperty(value = "")


  public String getConsumer() {
    return consumer;
  }

  public void setConsumer(String consumer) {
    this.consumer = consumer;
  }

  public SaleDTO items(List<SaleItemDTO> items) {
    this.items = items;
    return this;
  }

  public SaleDTO addItemsItem(SaleItemDTO itemsItem) {
    if (this.items == null) {
      this.items = new ArrayList<SaleItemDTO>();
    }
    this.items.add(itemsItem);
    return this;
  }

  /**
   * Get items
   * @return items
   **/
  @ApiModelProperty(value = "")

  @Valid

  public List<SaleItemDTO> getItems() {
    return items;
  }

  public void setItems(List<SaleItemDTO> items) {
    this.items = items;
  }

  public SaleDTO totalExVat(Double totalExVat) {
    this.totalExVat = totalExVat;
    return this;
  }

  /**
   * Get totalExVat
   * @return totalExVat
   **/
  @ApiModelProperty(value = "")


  public Double getTotalExVat() {
    return totalExVat;
  }

  public void setTotalExVat(Double totalExVat) {
    this.totalExVat = totalExVat;
  }

  public SaleDTO totalInclVat(Double totalInclVat) {
    this.totalInclVat = totalInclVat;
    return this;
  }

  /**
   * Get totalInclVat
   * @return totalInclVat
   **/
  @ApiModelProperty(value = "")


  public Double getTotalInclVat() {
    return totalInclVat;
  }

  public void setTotalInclVat(Double totalInclVat) {
    this.totalInclVat = totalInclVat;
  }

  public SaleDTO vat(Double vat) {
    this.vat = vat;
    return this;
  }

  /**
   * Get vat
   * @return vat
   **/
  @ApiModelProperty(value = "")


  public Double getVat() {
    return vat;
  }

  public void setVat(Double vat) {
    this.vat = vat;
  }

  public SaleDTO price(Double price) {
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

  public SaleDTO vatRate(Double vatRate) {
    this.vatRate = vatRate;
    return this;
  }

  /**
   * Get vatRate
   * @return vatRate
   **/
  @ApiModelProperty(value = "")


  public Double getVatRate() {
    return vatRate;
  }

  public void setVatRate(Double vatRate) {
    this.vatRate = vatRate;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SaleDTO sale = (SaleDTO) o;
    return
            Objects.equals(this.user, sale.user) &&
            Objects.equals(this.consumer, sale.consumer) &&
            Objects.equals(this.items, sale.items) &&
            Objects.equals(this.totalExVat, sale.totalExVat) &&
            Objects.equals(this.totalInclVat, sale.totalInclVat) &&
            Objects.equals(this.vat, sale.vat) &&
            Objects.equals(this.price, sale.price) &&
            Objects.equals(this.vatRate, sale.vatRate);
  }

  @Override
  public int hashCode() {
    return Objects.hash( user, consumer, items, totalExVat, totalInclVat, vat, price, vatRate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SaleDTO {\n");


    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    consumer: ").append(toIndentedString(consumer)).append("\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
    sb.append("    totalExVat: ").append(toIndentedString(totalExVat)).append("\n");
    sb.append("    totalInclVat: ").append(toIndentedString(totalInclVat)).append("\n");
    sb.append("    vat: ").append(toIndentedString(vat)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    vatRate: ").append(toIndentedString(vatRate)).append("\n");
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

