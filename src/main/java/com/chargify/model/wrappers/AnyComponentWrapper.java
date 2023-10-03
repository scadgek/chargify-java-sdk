package com.chargify.model.wrappers;

import com.chargify.model.Component;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public final class AnyComponentWrapper
{
  private final Component component;

  @JsonCreator
  public AnyComponentWrapper( @JsonProperty( "component" ) Component wrappedComponent )
  {
    this.component = wrappedComponent;
  }
}
