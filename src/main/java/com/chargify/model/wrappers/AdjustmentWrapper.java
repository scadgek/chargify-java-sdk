package com.chargify.model.wrappers;

import com.chargify.model.Adjustment;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public final class AdjustmentWrapper
{
  private final Adjustment adjustment;

  @JsonCreator
  public AdjustmentWrapper( @JsonProperty( "adjustment" ) Adjustment wrappedAdjustment )
  {
    this.adjustment = wrappedAdjustment;
  }
}
