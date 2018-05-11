package com.chargify.model.wrappers;

import com.chargify.model.Component;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class AnyComponentWrapper
{
  private final Component component;

  @JsonCreator
  public AnyComponentWrapper( @JsonProperty( "component" ) Component wrappedComponent )
  {
    this.component = wrappedComponent;
  }

  public Component getComponent()
  {
    return component;
  }

  @Override
  public String toString()
  {
    return "AnyComponentWrapper{" +
            "component=" + component +
            '}';
  }
}
