package com.chargify.model.wrappers;

import com.chargify.model.ComponentPricePointUpdate;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Data
@Setter( AccessLevel.NONE )
public final class ComponentPricePointUpdatesWrapper
{
  @JsonProperty( "components" )
  private final List<ComponentPricePointUpdate> pricePointUpdates;

  @JsonCreator
  public ComponentPricePointUpdatesWrapper(
          @JsonProperty( "components" ) ComponentPricePointUpdate... pricePointUpdates )
  {
    this.pricePointUpdates =  Arrays.asList( pricePointUpdates );
  }

  public ComponentPricePointUpdatesWrapper( List<ComponentPricePointUpdate> pricePointUpdates )
  {
    this.pricePointUpdates = pricePointUpdates;
  }
}
