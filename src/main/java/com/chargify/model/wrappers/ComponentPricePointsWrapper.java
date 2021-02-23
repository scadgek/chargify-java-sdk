package com.chargify.model.wrappers;

import com.chargify.model.PricePoint;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@ToString
public final class ComponentPricePointsWrapper
{
  @Getter
  private final Set<PricePoint> pricePoints;

  @JsonCreator
  public ComponentPricePointsWrapper( @JsonProperty( "price_points" ) Set<PricePoint> pricePoints )
  {
    this.pricePoints = pricePoints;
  }
}
