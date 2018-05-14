package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class ComponentPricePointUpdate
{
  private final int componentId;
  private final String pricePointHandle;

  @JsonCreator
  public ComponentPricePointUpdate( @JsonProperty( "component_id" ) int componentId,
                                    @JsonProperty( "price_point" ) String pricePointHandle )
  {
    this.componentId = componentId;
    this.pricePointHandle = pricePointHandle;
  }

  @JsonProperty( "component_id" )
  public int getComponentId()
  {
    return componentId;
  }

  @JsonProperty( "price_point" )
  public String getPricePointHandle()
  {
    return pricePointHandle;
  }

  @Override
  public String toString()
  {
    return "ComponentPricePointUpdate{" +
            "componentId='" + componentId + '\'' +
            ", pricePointHandle='" + pricePointHandle + '\'' +
            '}';
  }
}
