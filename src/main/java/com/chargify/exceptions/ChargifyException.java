package com.chargify.exceptions;

import java.util.Collection;

public class ChargifyException extends RuntimeException
{
  ChargifyException( String errorMessage )
  {
    super( errorMessage );
  }

  private ChargifyException( Collection<?> errorMessages )
  {
    super( String.join( " && ", errorMessages.toString() ) );
  }

  static ChargifyException fromErrors( Collection<?> errorMessages )
  {
    if( errorMessages.size() == 1 )
    {
      return fromError( errorMessages.iterator().next().toString() );
    }
    else
    {
      return new ChargifyException( errorMessages );
    }
  }

  private static ChargifyException fromError( String errorMessage )
  {
    return switch( errorMessage )
    {
      case PaymentProfileInUseException.MESSAGE -> new PaymentProfileInUseException();
      case MissingNameException.MESSAGE -> new MissingNameException();
      case ApiHandleNotUniqueException.MESSAGE -> new ApiHandleNotUniqueException();
      default -> new ChargifyException( errorMessage );
    };
  }
}
