package com.chargify.model.wrappers;

import com.chargify.model.Subscription;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public final class SubscriptionWrapper
{
  private final Subscription subscription;

  @JsonCreator
  public SubscriptionWrapper( @JsonProperty( "subscription" ) Subscription wrappedSubscription )
  {
    this.subscription = wrappedSubscription;
  }
}
