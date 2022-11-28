package com.chargify.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor( access = AccessLevel.PRIVATE )
public final class TransactionsKinds
{
  public static final String CHARGE = "charge";
  public static final String CREDIT = "credit";
  public static final String ADJUSTMENT = "adjustment";
  public static final String PAYMENT = "payment";
  public static final String REFUND = "refund";
}
