package com.chargify.model.wrappers;

import com.chargify.model.Subscription;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class SubscriptionWrapper
{
  private final Subscription subscription;

  @JsonCreator
  public SubscriptionWrapper(
          @JsonProperty( "subscription" )
                  Subscription wrappedSubscription )
  {
    this.subscription = wrappedSubscription;
  }

  public Subscription getSubscription()
  {
    return subscription;
  }
}
