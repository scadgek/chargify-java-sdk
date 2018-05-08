package com.chargify.exceptions;

public final class MissingNameException extends ChargifyException
{
  static final String MESSAGE = "Name: cannot be blank.";

  MissingNameException()
  {
    super( MESSAGE );
  }
}
