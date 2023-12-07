package com.chargify.model.wrappers;

import com.chargify.model.PricePoint;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public final class PricePointUpdateResultWrapper
{
  @JsonProperty( "price_point" )
  private final PricePoint pricePoint;

  @JsonCreator
  public PricePointUpdateResultWrapper( @JsonProperty( "price_point" ) PricePoint pricePoint )
  {
    this.pricePoint = pricePoint;
  }
}
