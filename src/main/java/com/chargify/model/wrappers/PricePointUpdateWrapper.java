package com.chargify.model.wrappers;

import com.chargify.model.PricePoint;
import com.chargify.model.PricePointUpdate;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public final class PricePointUpdateWrapper
{
  @Getter
  @JsonProperty( "price_point" )
  private final PricePointUpdate pricePoint;

  @JsonCreator
  public PricePointUpdateWrapper( @JsonProperty( "price_point" ) PricePointUpdate pricePoint )
  {
    this.pricePoint = pricePoint;
  }
}
