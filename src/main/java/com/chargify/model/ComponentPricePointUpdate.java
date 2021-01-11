package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public final class ComponentPricePointUpdate
{
  @JsonProperty( "component_id" )
  private int componentId;
  @JsonProperty( "price_point" )
  private String pricePointHandle;
  @JsonProperty( value = "message" )
  private String message;

  @JsonCreator
  public ComponentPricePointUpdate()
  {
  }

  public ComponentPricePointUpdate( int componentId, String pricePointHandle )
  {
    this( componentId, pricePointHandle, null );
  }

  public ComponentPricePointUpdate( int componentId, String pricePointHandle, String message )
  {
    this.componentId = componentId;
    this.pricePointHandle = pricePointHandle;
    this.message = message;
  }
}
