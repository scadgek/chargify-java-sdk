package com.chargify.model.wrappers;

import com.chargify.model.Component;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class QuantityBasedComponentWrapper implements ComponentWrapper
{
  private final Component component;

  @JsonCreator
  public QuantityBasedComponentWrapper( @JsonProperty( "quantity_based_component" ) Component wrappedComponent )
  {
    this.component = wrappedComponent;
  }

  @JsonProperty( "quantity_based_component" )
  public Component getComponent()
  {
    return component;
  }

  @Override
  public String toString()
  {
    return "QuantityBasedComponentWrapper{" +
            "component=" + component +
            '}';
  }
}
