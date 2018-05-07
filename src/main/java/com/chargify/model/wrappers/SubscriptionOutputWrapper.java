package com.chargify.model.wrappers;

import com.chargify.model.SubscriptionOutput;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class SubscriptionOutputWrapper
{
  private final SubscriptionOutput subscription;

  @JsonCreator
  public SubscriptionOutputWrapper(
          @JsonProperty( "subscription" )
                  SubscriptionOutput wrappedSubscription )
  {
    this.subscription = wrappedSubscription;
  }

  public SubscriptionOutput getSubscription()
  {
    return subscription;
  }
}
