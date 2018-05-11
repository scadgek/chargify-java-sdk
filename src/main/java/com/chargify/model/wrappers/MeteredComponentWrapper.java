package com.chargify.model.wrappers;

import com.chargify.model.Component;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class MeteredComponentWrapper implements ComponentWrapper
{
  private final Component component;

  @JsonCreator
  public MeteredComponentWrapper( @JsonProperty( "metered_component" ) Component component )
  {
    this.component = component;
  }

  @JsonProperty( "metered_component" )
  public Component getComponent()
  {
    return component;
  }

  @Override
  public String toString()
  {
    return "MeteredComponentWrapper{" +
            "component=" + component +
            '}';
  }
}
