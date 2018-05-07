package com.chargify.model.wrappers;

import com.chargify.model.OnOffComponentCreateInput;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class OnOffComponentCreateInputWrapper
{
  @JsonProperty( "on_off_component" )
  private final OnOffComponentCreateInput onOffComponentCreateInput;

  @JsonCreator
  public OnOffComponentCreateInputWrapper(
          @JsonProperty( "on_off_component" )
                  OnOffComponentCreateInput onOffComponentCreateInput )
  {
    this.onOffComponentCreateInput = onOffComponentCreateInput;
  }

  public OnOffComponentCreateInput getOnOffComponentCreateInput()
  {
    return onOffComponentCreateInput;
  }
}
