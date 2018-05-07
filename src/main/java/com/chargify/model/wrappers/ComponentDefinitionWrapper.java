package com.chargify.model.wrappers;

import com.chargify.model.ComponentDefinition;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class ComponentDefinitionWrapper
{
  private final ComponentDefinition component;

  @JsonCreator
  public ComponentDefinitionWrapper(
          @JsonProperty( "component" )
                  ComponentDefinition wrappedComponent )
  {
    this.component = wrappedComponent;
  }

  public ComponentDefinition getComponent()
  {
    return component;
  }
}
