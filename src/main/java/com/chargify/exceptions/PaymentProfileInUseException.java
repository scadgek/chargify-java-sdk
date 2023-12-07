package com.chargify.exceptions;

public final class PaymentProfileInUseException extends ChargifyException
{
  static final String MESSAGE = "The payment profile is in use by one or more subscriptions and cannot be deleted";

  PaymentProfileInUseException()
  {
    super( MESSAGE );
  }
}
