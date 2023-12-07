package com.chargify.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor( access = AccessLevel.PRIVATE )
public final class PaymentType
{
  public static final String CREDIT_CARD = "credit_card";
  public static final String BANK_ACCOUNT = "bank_account";
  public static final String PAYPAL_ACCOUNT = "paypal_account";
}
