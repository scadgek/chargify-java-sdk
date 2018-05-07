package com.chargify.model.wrappers;

import com.chargify.model.SubscriptionComponent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class SubscriptionComponentWrapper
{
  private final SubscriptionComponent component;

  @JsonCreator
  public SubscriptionComponentWrapper( @JsonProperty( "component" ) SubscriptionComponent wrappedComponent )
  {
    this.component = wrappedComponent;
  }

  public SubscriptionComponent getComponent()
  {
    return component;
  }
}
