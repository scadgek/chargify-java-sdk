package com.chargify.model.wrappers;

import com.chargify.model.Component;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class OnOffComponentWrapper implements ComponentWrapper
{
  private final Component component;

  @JsonCreator
  public OnOffComponentWrapper( @JsonProperty( "on_off_component" ) Component component )
  {
    this.component = component;
  }

  @JsonProperty( "on_off_component" )
  public Component getComponent()
  {
    return component;
  }

  @Override
  public String toString()
  {
    return "OnOffComponentWrapper{" +
            "component=" + component +
            '}';
  }
}
