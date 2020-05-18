package com.chargify.model.wrappers;

import com.chargify.model.CreateSubscription;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public final class CreateSubscriptionWrapper
{
  private final CreateSubscription subscription;

  @JsonCreator
  public CreateSubscriptionWrapper( @JsonProperty( "subscription" ) CreateSubscription wrappedSubscription )
  {
    this.subscription = wrappedSubscription;
  }

  public CreateSubscription getSubscription()
  {
    return subscription;
  }
}
