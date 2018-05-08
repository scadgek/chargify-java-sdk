package com.chargify.exceptions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class ChargifyError
{
  private final List<String> errors;

  @JsonCreator
  public ChargifyError( @JsonProperty( "errors" ) List<String> errors )
  {
    this.errors = errors;
  }

  ChargifyException exception()
  {
    return ChargifyException.fromErrors( errors );
  }
}
