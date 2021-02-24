/*
 * ********************************************************************************************************************
 *  <p/>
 *  BACKENDLESS.COM CONFIDENTIAL
 *  <p/>
 *  ********************************************************************************************************************
 *  <p/>
 *  Copyright 2012 BACKENDLESS.COM. All Rights Reserved.
 *  <p/>
 *  NOTICE:  All information contained herein is, and remains the property of Backendless.com and its suppliers,
 *  if any.  The intellectual and technical concepts contained herein are proprietary to Backendless.com and its
 *  suppliers and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret
 *  or copyright law. Dissemination of this information or reproduction of this material is strictly forbidden
 *  unless prior written permission is obtained from Backendless.com.
 *  <p/>
 *  ********************************************************************************************************************
 */

package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@JsonInclude( JsonInclude.Include.NON_NULL )
public class Subscription implements Serializable
{
  private String id;

  private String state;

  @JsonProperty( "balance_in_cents" )
  private Integer balanceInCents;

  @JsonProperty( "total_revenue_in_cents" )
  private Integer totalRevenueInCents;

  @JsonProperty( "product_price_in_cents" )
  private Integer productPriceInCents;

  @JsonProperty( "product_version_number" )
  private Integer productVersionNumber;

  @JsonProperty( "current_period_ends_at" )
  private Date currentPeriodEndsAt;

  @JsonProperty( "next_assessment_at" )
  private ZonedDateTime nextAssessmentAt;

  @JsonProperty( "next_billing_at" )
  private ZonedDateTime nextBillingAt;

  @JsonProperty( "trial_started_at" )
  private Date trialStartedAt;

  @JsonProperty( "trial_ended_at" )
  private Date trialEndedAt;

  @JsonProperty( "activated_at" )
  private Date activatedAt;

  @JsonProperty( "expires_at" )
  private Date expiresAt;

  @JsonProperty( "created_at" )
  private Date createdAt;

  @JsonProperty( "updated_at" )
  private Date updatedAt;

  @JsonProperty( "cancellation_message" )
  private String cancellationMessage;

  @JsonProperty( "cancellation_method" )
  private String cancellationMethod;

  @JsonProperty( "cancel_at_end_of_period" )
  private Boolean cancelAtEndOfPeriod;

  @JsonProperty( "canceled_at" )
  private Date canceledAt;

  @JsonProperty( "current_period_started_at" )
  private Date currentPeriodStartedAt;

  @JsonProperty( "customer_attributes" )
  private Customer customerAttributes;

  @JsonProperty( "customer_id" )
  private String customerId;

  @JsonProperty( "customer_reference" )
  private String customerReference;

  @JsonProperty( "product_change_delayed" )
  private boolean productChangeDelayed;

  @JsonProperty( "previous_state" )
  private String previousState;

  @JsonProperty( "product_handle" )
  private String productHandle;

  @JsonProperty( "product_id" )
  private String productId;

  @JsonProperty( "signup_payment_id" )
  private Integer signupPaymentId;

  @JsonProperty( "signup_revenue" )
  private String signupRevenue;

  @JsonProperty( "delayed_cancel_at" )
  private Date delayedCancelAt;

  @JsonProperty( "coupon_code" )
  private String couponCode;

  @JsonProperty( "payment_collection_at" )
  private String paymentCollectionMethod;

  @JsonProperty( "payment_profile_id" )
  private String paymentProfileId;

  @JsonProperty( "snap_day" )
  private String snapDay;

  @JsonProperty( "reason_code" )
  private String reasonCode;

  private Customer customer;

  private SubscriptionProduct product;

  @JsonProperty( "credit_card" )
  private CreditCard creditCard;

  @JsonProperty( "payment_type" )
  private String paymentType;

  @JsonProperty( "referral_code" )
  private String referralCode;

  @JsonProperty( "ref" )
  private String reference;

  @JsonProperty( "next_product_id" )
  private String nextProductId;

  @JsonProperty( "next_product_handle" )
  private String nextProductHandle;

  @JsonProperty( "next_product_price_point_id" )
  private Integer nextProductPricePointId;

  @JsonProperty( "coupon_use_count" )
  private Integer couponUseCount;

  @JsonProperty( "coupon_uses_allowed" )
  private Integer couponUsesAllowed;

  private List<SubscriptionComponent> components;

  private Map<String, String> metafields = new HashMap<>();
}
