package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonInclude( JsonInclude.Include.NON_NULL )
public final class PricePoint implements Serializable
{
  private String name;
  private String handle;
  private String pricingScheme;
  private Price[] prices;

  @JsonCreator
  public PricePoint()
  {
  }

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public String getHandle()
  {
    return handle;
  }

  public void setHandle( String handle )
  {
    this.handle = handle;
  }

  @JsonProperty( "pricing_scheme" )
  public String getPricingScheme()
  {
    return pricingScheme;
  }

  public void setPricingScheme( String pricingScheme )
  {
    this.pricingScheme = pricingScheme;
  }

  @JsonProperty( "prices" )
  public Price[] getPrices()
  {
    return prices;
  }

  public void setPrices( Price[] prices )
  {
    this.prices = prices;
  }
}
