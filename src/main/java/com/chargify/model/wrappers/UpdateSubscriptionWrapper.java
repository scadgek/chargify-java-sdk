package com.chargify.model.wrappers;

import com.chargify.model.UpdateSubscription;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public final class UpdateSubscriptionWrapper
{
  private final UpdateSubscription subscription;

  @JsonCreator
  public UpdateSubscriptionWrapper( @JsonProperty( "subscription" ) UpdateSubscription wrappedSubscription )
  {
    this.subscription = wrappedSubscription;
  }
}
