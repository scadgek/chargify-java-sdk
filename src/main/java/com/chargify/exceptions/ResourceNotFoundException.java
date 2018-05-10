package com.chargify.exceptions;

public class ResourceNotFoundException extends ChargifyException
{
  static final String MESSAGE = "Requested resource not found";

  ResourceNotFoundException()
  {
    super( MESSAGE );
  }
}
