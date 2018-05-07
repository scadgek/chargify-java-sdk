package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude( JsonInclude.Include.NON_NULL )
public class OnOffComponentCreateInput extends ComponentCreateInput
{
  @JsonProperty("price_attributes")
  private PriceAttributes priceAttributes;

  public PriceAttributes getPriceAttributes()
  {
    return priceAttributes;
  }

  public OnOffComponentCreateInput setPriceAttributes( PriceAttributes priceAttributes )
  {
    this.priceAttributes = priceAttributes;
    return this;
  }

  public OnOffComponentCreateInput setPrice( Double price )
  {
    priceAttributes = new PriceAttributes().setUnitPrice( price );
    return this;
  }

  public static class PriceAttributes
  {
    @JsonProperty( "unit_price" )
    private Double unitPrice;
    @JsonProperty( "starting_quantity" )
    private String startingQuantity = "0";

    public Double getUnitPrice()
    {
      return unitPrice;
    }

    public PriceAttributes setUnitPrice( Double unitPrice )
    {
      this.unitPrice = unitPrice;
      return this;
    }

    public String getStartingQuantity()
    {
      return startingQuantity;
    }

    public PriceAttributes setStartingQuantity( String startingQuantity )
    {
      this.startingQuantity = startingQuantity;
      return this;
    }
  }
}
