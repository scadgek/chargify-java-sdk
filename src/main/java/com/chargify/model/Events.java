package com.chargify.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor( access = AccessLevel.PRIVATE )
public final class Events
{
  public static final String BILLING_DATE_CHANGE = "billing_date_change";
  public static final String COMPONENT_ALLOCATION_CHANGE = "component_allocation_change";
  public static final String CUSTOMER_CREATE = "customer_create";
  public static final String CUSTOMER_UPDATE = "customer_update";
  public static final String DUNNING_STEP_REACHED = "dunning_step_reached";
  public static final String EXPIRATION_DATE_CHANGE = "expiration_date_change";
  public static final String EXPIRING_CARD = "expiring_card";
  public static final String INVOICE_ISSUED = "invoice_issued";
  public static final String METERED_USAGE = "metered_usage";
  public static final String PAYMENT_FAILURE = "payment_failure";
  public static final String PAYMENT_SUCCESS = "payment_success";
  public static final String PREPAID_USAGE = "prepaid_usage";
  public static final String RENEWAL_FAILURE = "renewal_failure";
  public static final String RENEWAL_SUCCESS = "renewal_success";
  public static final String SIGNUP_SUCCESS = "signup_success";
  public static final String SIGNUP_FAILURE = "signup_failure";
  public static final String SUBSCRIPTION_CARD_UPDATE = "subscription_card_update";
  public static final String SUBSCRIPTION_PRODUCT_CHANGE = "subscription_product_change";
  public static final String SUBSCRIPTION_STATE_CHANGE = "subscription_state_change";
  public static final String UPCOMING_RENEWAL_NOTICE = "upcoming_renewal_notice";
  public static final String UPGRADE_DOWNGRADE_FAILURE = "upgrade_downgrade_failure";
  public static final String UPGRADE_DOWNGRADE_SUCCESS = "upgrade_downgrade_success";
  public static final String PENDING_CANCELLATION_CHANGE = "pending_cancellation_change";
}
