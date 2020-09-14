package com.chargify.model.wrappers;

import com.chargify.model.SubscriptionChargeResult;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;


@ToString
public final class SubscriptionChargeWrapper
{
  @Getter
  private final SubscriptionChargeResult subscriptionChargeResult;

  @JsonCreator
  public SubscriptionChargeWrapper( @JsonProperty( "charge" ) SubscriptionChargeResult subscriptionChargeResult )
  {
    this.subscriptionChargeResult = subscriptionChargeResult;
  }
}
