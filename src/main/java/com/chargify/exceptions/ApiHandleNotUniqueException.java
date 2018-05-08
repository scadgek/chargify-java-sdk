package com.chargify.exceptions;

public final class ApiHandleNotUniqueException extends ChargifyException
{
  static final String MESSAGE = "API Handle: must be unique - that value has been taken.";

  ApiHandleNotUniqueException()
  {
    super( MESSAGE );
  }
}
