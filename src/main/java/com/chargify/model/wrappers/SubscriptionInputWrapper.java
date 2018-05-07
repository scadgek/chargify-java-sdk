package com.chargify.model.wrappers;

import com.chargify.model.SubscriptionInput;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class SubscriptionInputWrapper
{
  private final SubscriptionInput subscription;

  @JsonCreator
  public SubscriptionInputWrapper(
          @JsonProperty( "subscription" )
                  SubscriptionInput wrappedSubscription )
  {
    this.subscription = wrappedSubscription;
  }

  public SubscriptionInput getSubscription()
  {
    return subscription;
  }
}
