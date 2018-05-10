package com.chargify.exceptions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class ChargifyError
{
  private final Object errors;

  @JsonCreator
  public ChargifyError( @JsonProperty( "errors" ) Object errors )
  {
    this.errors = errors;
  }

  ChargifyException exception()
  {
    if( errors instanceof Collection )
    {
      return ChargifyException.fromErrors( (Collection<String>) errors );
    }
    else if( errors instanceof Map )
    {
      List<String> errorsList = new ArrayList<>();
      ((Map) errors).forEach( ( key, value ) -> errorsList.add( key + ": " + value ) );
      return ChargifyException.fromErrors( errorsList );
    }
    else
    {
      return ChargifyException.fromErrors( Collections.singleton( errors.toString() ) );
    }
  }
}
