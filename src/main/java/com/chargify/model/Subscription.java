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

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
  private Date nextAssessmentAt;

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

  @JsonProperty( "snap_day" )
  private String snapDay;

  @JsonProperty( "reason_code" )
  private String reasonCode;

  private Customer customer;

  private Product product;

  @JsonProperty( "credit_card" )
  private CreditCard creditCard;

  @JsonProperty( "payment_type" )
  private String paymentType;

  @JsonProperty( "referral_code" )
  private String referralCode;

  @JsonProperty( "next_product_id" )
  private String nextProductId;

  @JsonProperty( "coupon_use_count" )
  private Integer couponUseCount;

  @JsonProperty( "coupon_uses_allowed" )
  private Integer couponUsesAllowed;

  private Map<String, String> metafields = new HashMap<>();

  public String getId()
  {
    return id;
  }

  public void setId( String id )
  {
    this.id = id;
  }

  public String getState()
  {
    return state;
  }

  public void setState( String state )
  {
    this.state = state;
  }

  public Integer getBalanceInCents()
  {
    return balanceInCents;
  }

  public void setBalanceInCents( Integer balanceInCents )
  {
    this.balanceInCents = balanceInCents;
  }

  public Integer getTotalRevenueInCents()
  {
    return totalRevenueInCents;
  }

  public void setTotalRevenueInCents( Integer totalRevenueInCents )
  {
    this.totalRevenueInCents = totalRevenueInCents;
  }

  public Integer getProductPriceInCents()
  {
    return productPriceInCents;
  }

  public void setProductPriceInCents( Integer productPriceInCents )
  {
    this.productPriceInCents = productPriceInCents;
  }

  public Integer getProductVersionNumber()
  {
    return productVersionNumber;
  }

  public void setProductVersionNumber( Integer productVersionNumber )
  {
    this.productVersionNumber = productVersionNumber;
  }

  public Date getCurrentPeriodEndsAt()
  {
    return currentPeriodEndsAt;
  }

  public void setCurrentPeriodEndsAt( Date currentPeriodEndsAt )
  {
    this.currentPeriodEndsAt = currentPeriodEndsAt;
  }

  public Date getNextAssessmentAt()
  {
    return nextAssessmentAt;
  }

  public void setNextAssessmentAt( Date nextAssessmentAt )
  {
    this.nextAssessmentAt = nextAssessmentAt;
  }

  public Date getTrialStartedAt()
  {
    return trialStartedAt;
  }

  public void setTrialStartedAt( Date trialStartedAt )
  {
    this.trialStartedAt = trialStartedAt;
  }

  public Date getTrialEndedAt()
  {
    return trialEndedAt;
  }

  public void setTrialEndedAt( Date trialEndedAt )
  {
    this.trialEndedAt = trialEndedAt;
  }

  public Date getActivatedAt()
  {
    return activatedAt;
  }

  public void setActivatedAt( Date activatedAt )
  {
    this.activatedAt = activatedAt;
  }

  public Date getExpiresAt()
  {
    return expiresAt;
  }

  public void setExpiresAt( Date expiresAt )
  {
    this.expiresAt = expiresAt;
  }

  public Date getCreatedAt()
  {
    return createdAt;
  }

  public void setCreatedAt( Date createdAt )
  {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt()
  {
    return updatedAt;
  }

  public void setUpdatedAt( Date updatedAt )
  {
    this.updatedAt = updatedAt;
  }

  public String getCancellationMessage()
  {
    return cancellationMessage;
  }

  public void setCancellationMessage( String cancellationMessage )
  {
    this.cancellationMessage = cancellationMessage;
  }

  public String getCancellationMethod()
  {
    return cancellationMethod;
  }

  public void setCancellationMethod( String cancellationMethod )
  {
    this.cancellationMethod = cancellationMethod;
  }

  public Boolean getCancelAtEndOfPeriod()
  {
    return cancelAtEndOfPeriod;
  }

  public void setCancelAtEndOfPeriod( Boolean cancelAtEndOfPeriod )
  {
    this.cancelAtEndOfPeriod = cancelAtEndOfPeriod;
  }

  public Date getCanceledAt()
  {
    return canceledAt;
  }

  public void setCanceledAt( Date canceledAt )
  {
    this.canceledAt = canceledAt;
  }

  public Date getCurrentPeriodStartedAt()
  {
    return currentPeriodStartedAt;
  }

  public void setCurrentPeriodStartedAt( Date currentPeriodStartedAt )
  {
    this.currentPeriodStartedAt = currentPeriodStartedAt;
  }

  public String getPreviousState()
  {
    return previousState;
  }

  public void setPreviousState( String previousState )
  {
    this.previousState = previousState;
  }

  public Integer getSignupPaymentId()
  {
    return signupPaymentId;
  }

  public void setSignupPaymentId( Integer signupPaymentId )
  {
    this.signupPaymentId = signupPaymentId;
  }

  public String getSignupRevenue()
  {
    return signupRevenue;
  }

  public void setSignupRevenue( String signupRevenue )
  {
    this.signupRevenue = signupRevenue;
  }

  public Date getDelayedCancelAt()
  {
    return delayedCancelAt;
  }

  public void setDelayedCancelAt( Date delayedCancelAt )
  {
    this.delayedCancelAt = delayedCancelAt;
  }

  public String getCouponCode()
  {
    return couponCode;
  }

  public void setCouponCode( String couponCode )
  {
    this.couponCode = couponCode;
  }

  public String getPaymentCollectionMethod()
  {
    return paymentCollectionMethod;
  }

  public void setPaymentCollectionMethod( String paymentCollectionMethod )
  {
    this.paymentCollectionMethod = paymentCollectionMethod;
  }

  public String getSnapDay()
  {
    return snapDay;
  }

  public void setSnapDay( String snapDay )
  {
    this.snapDay = snapDay;
  }

  public String getReasonCode()
  {
    return reasonCode;
  }

  public void setReasonCode( String reasonCode )
  {
    this.reasonCode = reasonCode;
  }

  public Customer getCustomer()
  {
    return customer;
  }

  public void setCustomer( Customer customer )
  {
    this.customer = customer;
  }

  public Product getProduct()
  {
    return product;
  }

  public void setProduct( Product product )
  {
    this.product = product;
  }

  public CreditCard getCreditCard()
  {
    return creditCard;
  }

  public void setCreditCard( CreditCard creditCard )
  {
    this.creditCard = creditCard;
  }

  public String getPaymentType()
  {
    return paymentType;
  }

  public void setPaymentType( String paymentType )
  {
    this.paymentType = paymentType;
  }

  public String getReferralCode()
  {
    return referralCode;
  }

  public void setReferralCode( String referralCode )
  {
    this.referralCode = referralCode;
  }

  public String getNextProductId()
  {
    return nextProductId;
  }

  public void setNextProductId( String nextProductId )
  {
    this.nextProductId = nextProductId;
  }

  public Integer getCouponUseCount()
  {
    return couponUseCount;
  }

  public void setCouponUseCount( Integer couponUseCount )
  {
    this.couponUseCount = couponUseCount;
  }

  public Integer getCouponUsesAllowed()
  {
    return couponUsesAllowed;
  }

  public void setCouponUsesAllowed( Integer couponUsesAllowed )
  {
    this.couponUsesAllowed = couponUsesAllowed;
  }

  public String getProductId()
  {
    return productId;
  }

  public void setProductId( String productId )
  {
    this.productId = productId;
  }

  public String getCustomerId()
  {
    return customerId;
  }

  public void setCustomerId( String customerId )
  {
    this.customerId = customerId;
  }

  public String getCustomerReference()
  {
    return customerReference;
  }

  public void setCustomerReference( String customerReference )
  {
    this.customerReference = customerReference;
  }

  public String getProductHandle()
  {
    return productHandle;
  }

  public void setProductHandle( String productHandle )
  {
    this.productHandle = productHandle;
  }

  public Customer getCustomerAttributes()
  {
    return customerAttributes;
  }

  public void setCustomerAttributes( Customer customerAttributes )
  {
    this.customerAttributes = customerAttributes;
  }

  public boolean isProductChangeDelayed()
  {
    return productChangeDelayed;
  }

  public void setProductChangeDelayed( boolean productChangeDelayed )
  {
    this.productChangeDelayed = productChangeDelayed;
  }

  @Override
  public String toString()
  {
    return "Subscription{" +
            "id='" + id + '\'' +
            ", state='" + state + '\'' +
            ", balanceInCents=" + balanceInCents +
            ", totalRevenueInCents=" + totalRevenueInCents +
            ", productPriceInCents=" + productPriceInCents +
            ", productVersionNumber=" + productVersionNumber +
            ", currentPeriodEndsAt=" + currentPeriodEndsAt +
            ", nextAssessmentAt=" + nextAssessmentAt +
            ", trialStartedAt=" + trialStartedAt +
            ", trialEndedAt=" + trialEndedAt +
            ", activatedAt=" + activatedAt +
            ", expiresAt=" + expiresAt +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            ", cancellationMessage='" + cancellationMessage + '\'' +
            ", cancellationMethod='" + cancellationMethod + '\'' +
            ", cancelAtEndOfPeriod=" + cancelAtEndOfPeriod +
            ", canceledAt=" + canceledAt +
            ", currentPeriodStartedAt=" + currentPeriodStartedAt +
            ", customerAttributes=" + customerAttributes +
            ", customerId='" + customerId + '\'' +
            ", customerReference='" + customerReference + '\'' +
            ", productChangeDelayed=" + productChangeDelayed +
            ", previousState='" + previousState + '\'' +
            ", productHandle='" + productHandle + '\'' +
            ", productId='" + productId + '\'' +
            ", signupPaymentId=" + signupPaymentId +
            ", signupRevenue='" + signupRevenue + '\'' +
            ", delayedCancelAt=" + delayedCancelAt +
            ", couponCode='" + couponCode + '\'' +
            ", paymentCollectionMethod='" + paymentCollectionMethod + '\'' +
            ", snapDay='" + snapDay + '\'' +
            ", reasonCode='" + reasonCode + '\'' +
            ", customer=" + customer +
            ", product=" + product +
            ", creditCard=" + creditCard +
            ", paymentType='" + paymentType + '\'' +
            ", referralCode='" + referralCode + '\'' +
            ", nextProductId='" + nextProductId + '\'' +
            ", couponUseCount=" + couponUseCount +
            ", couponUsesAllowed=" + couponUsesAllowed +
            '}';
  }

  public Map<String, String> getMetafields()
  {
    return metafields;
  }

  public void setMetafields( Map<String, String> metafields )
  {
    this.metafields = metafields;
  }
}
