package com.chargify.model.wrappers;

import com.chargify.model.QuantityBasedComponentCreateInput;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class QuantityBasedComponentCreateInputWrapper
{
  @JsonProperty( "quantity_based_component" )
  private final QuantityBasedComponentCreateInput componentCreateInput;

  @JsonCreator
  public QuantityBasedComponentCreateInputWrapper(
          @JsonProperty( "quantity_based_component" )
                  QuantityBasedComponentCreateInput componentCreateInput )
  {
    this.componentCreateInput = componentCreateInput;
  }

  public QuantityBasedComponentCreateInput getComponentCreateInput()
  {
    return componentCreateInput;
  }
}
