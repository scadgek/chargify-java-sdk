package com.chargify.exceptions;

import java.util.Collection;

public class ChargifyException extends RuntimeException
{
  ChargifyException( String errorMessage )
  {
    super( errorMessage );
  }

  private ChargifyException( Collection<String> errorMessages )
  {
    super( String.join( " && ", errorMessages ) );
  }

  static ChargifyException fromErrors( Collection<String> errorMessages )
  {
    if( errorMessages.size() == 1 )
    {
      return fromError( errorMessages.iterator().next() );
    }
    else
    {
      return new ChargifyException( errorMessages );
    }
  }

  private static ChargifyException fromError( String errorMessage )
  {
    switch( errorMessage )
    {
      case MissingNameException.MESSAGE:
        return new MissingNameException();
      case ApiHandleNotUniqueException.MESSAGE:
        return new ApiHandleNotUniqueException();
      default:
        return new ChargifyException( errorMessage );
    }
  }
}
