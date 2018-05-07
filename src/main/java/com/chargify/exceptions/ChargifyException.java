package com.chargify.exceptions;

import java.util.Collection;

public class ChargifyException extends RuntimeException
{
  public ChargifyException( String errorMessage )
  {
    super( errorMessage );
  }

  public ChargifyException( Collection<String> errorMessages )
  {
    super( String.join( " ", errorMessages ) );
  }
}
