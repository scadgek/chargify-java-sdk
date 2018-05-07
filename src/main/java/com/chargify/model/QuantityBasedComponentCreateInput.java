package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude( JsonInclude.Include.NON_NULL )
public class QuantityBasedComponentCreateInput extends ComponentCreateInput
{
  @JsonProperty("pricing_scheme")
  private String pricingScheme = "per_unit";

  @JsonProperty("unit_price")
  private Double price;

  @JsonProperty("unit_name")
  private String unitName = "Component";

  @Override
  public ComponentCreateInput setPrice( Double price )
  {
     this.price = price;
    return this;
  }

  public Double getPrice()
  {
    return price;
  }

  public String getUnitName()
  {
    return unitName;
  }

  public String getPricingScheme()
  {
    return pricingScheme;
  }
}
