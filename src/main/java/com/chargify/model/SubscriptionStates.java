package com.chargify.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor( access = AccessLevel.PRIVATE )
public final class SubscriptionStates
{
  public static final String ACTIVE = "active";
  public static final String CANCELED = "canceled";
  public static final String EXPIRED = "expired";
  public static final String ON_HOLD = "on_hold";
  public static final String PAST_DUE = "past_due";
  public static final String SOFT_FAILURE = "soft_failure";
  public static final String TRAILING = "trialing";
  public static final String TRIAL_ENDED = "trial_ended";
  public static final String UNPAID = "unpaid";
}
