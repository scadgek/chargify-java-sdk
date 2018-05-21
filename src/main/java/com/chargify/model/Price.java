package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude( JsonInclude.Include.NON_NULL )
public final class Price
{
  private Integer startingQuantity;
  private Integer endingQuantity;
  private Double unitPrice;

  @JsonCreator
  public Price()
  {
  }

  @JsonProperty( "starting_quantity" )
  public Integer getStartingQuantity()
  {
    return startingQuantity;
  }

  public void setStartingQuantity( Integer startingQuantity )
  {
    this.startingQuantity = startingQuantity;
  }

  @JsonProperty( "ending_quantity" )
  public Integer getEndingQuantity()
  {
    return endingQuantity;
  }

  public void setEndingQuantity( Integer endingQuantity )
  {
    this.endingQuantity = endingQuantity;
  }

  @JsonProperty( "unit_price" )
  public Double getUnitPrice()
  {
    return unitPrice;
  }

  public void setUnitPrice( Double unitPrice )
  {
    this.unitPrice = unitPrice;
  }
}
