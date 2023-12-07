package com.chargify.exceptions;

import com.fasterxml.jackson.annotation.JsonAlias;
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
  public ChargifyError( @JsonProperty( "errors" ) @JsonAlias( "error" ) Object errors )
  {
    this.errors = errors;
  }

  ChargifyException exception()
  {
    if( errors instanceof Collection<?> errorsAsStrings )
    {
      return ChargifyException.fromErrors( errorsAsStrings );
    }
    else if( errors instanceof Map<?, ?> errorsAsMap )
    {
      List<String> errorsList = new ArrayList<>();
      errorsAsMap.forEach( ( key, value ) -> errorsList.add( key + ": " + value ) );
      return ChargifyException.fromErrors( errorsList );
    }
    else
    {
      return ChargifyException.fromErrors( Collections.singleton( errors.toString() ) );
    }
  }
}
