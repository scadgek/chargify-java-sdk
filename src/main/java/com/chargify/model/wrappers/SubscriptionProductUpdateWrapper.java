package com.chargify.model.wrappers;

import com.chargify.model.SubscriptionProductUpdate;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public final class SubscriptionProductUpdateWrapper
{
  @Getter
  private final SubscriptionProductUpdate subscription;

  @JsonCreator
  public SubscriptionProductUpdateWrapper( @JsonProperty( "subscription" )
                                                   SubscriptionProductUpdate wrappedSubscription )
  {
    this.subscription = wrappedSubscription;
  }
}
