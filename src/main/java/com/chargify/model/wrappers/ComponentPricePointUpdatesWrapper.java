package com.chargify.model.wrappers;

import com.chargify.model.ComponentPricePointUpdate;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public final class ComponentPricePointUpdatesWrapper
{
  private final ComponentPricePointUpdate[] pricePointUpdates;

  @JsonCreator
  public ComponentPricePointUpdatesWrapper(
          @JsonProperty( "components" ) ComponentPricePointUpdate... pricePointUpdates )
  {
    this.pricePointUpdates = pricePointUpdates;
  }

  @JsonProperty( "components" )
  public ComponentPricePointUpdate[] getPricePointUpdates()
  {
    return pricePointUpdates;
  }

  @Override
  public String toString()
  {
    return "ComponentPricePointUpdatesWrapper{" +
            "pricePointUpdates=" + Arrays.toString( pricePointUpdates ) +
            '}';
  }
}
