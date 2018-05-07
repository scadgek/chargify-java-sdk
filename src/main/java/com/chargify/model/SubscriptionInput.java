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

import java.util.List;

@JsonInclude( JsonInclude.Include.NON_NULL )
public class SubscriptionInput
{
  @JsonProperty( "product_handle" )
  private String productHandle;

  @JsonProperty( "product_id" )
  private String productId;

  @JsonProperty( "customer_id" )
  private String customerId;

  @JsonProperty( "customer_reference" )
  private String customerReference;

  @JsonProperty( "customer_attributes" )
  private Customer customerAttributes;

  @JsonProperty( "payment_profile_id" )
  private String paymentProfileId;

  @JsonProperty( "cancellation_message" )
  private String cancellationMessage;

  @JsonProperty( "next_billing_at" )
  private String nextBillingAt;

  @JsonProperty( "vat_number" )
  private String vatNumber;

  @JsonProperty( "coupon_code" )
  private String couponCode;

  @JsonProperty( "payment_profile_attributes" )
  private PaymentProfileAttributes paymentProfileAttributes;

  @JsonProperty( "metafields" )
  private SubscriptionMetafields metafields;

  @JsonProperty( "ref" )
  private String reference;

  private List<ProductComponentItem> components;

  public String getProductHandle()
  {
    return productHandle;
  }

  public void setProductHandle( String productHandle )
  {
    this.productHandle = productHandle;
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

  public Customer getCustomerAttributes()
  {
    return customerAttributes;
  }

  public void setCustomerAttributes( Customer customerAttributes )
  {
    this.customerAttributes = customerAttributes;
  }

  public String getPaymentProfileId()
  {
    return paymentProfileId;
  }

  public void setPaymentProfileId( String paymentProfileId )
  {
    this.paymentProfileId = paymentProfileId;
  }

  public String getCancellationMessage()
  {
    return cancellationMessage;
  }

  public void setCancellationMessage( String cancellationMessage )
  {
    this.cancellationMessage = cancellationMessage;
  }

  public String getNextBillingAt()
  {
    return nextBillingAt;
  }

  public void setNextBillingAt( String nextBillingAt )
  {
    this.nextBillingAt = nextBillingAt;
  }

  public String getVatNumber()
  {
    return vatNumber;
  }

  public void setVatNumber( String vatNumber )
  {
    this.vatNumber = vatNumber;
  }

  public String getCouponCode()
  {
    return couponCode;
  }

  public void setCouponCode( String couponCode )
  {
    this.couponCode = couponCode;
  }

  public PaymentProfileAttributes getPaymentProfileAttributes()
  {
    return paymentProfileAttributes;
  }

  public void setPaymentProfileAttributes( PaymentProfileAttributes paymentProfileAttributes )
  {
    this.paymentProfileAttributes = paymentProfileAttributes;
  }

  public List<ProductComponentItem> getComponents()
  {
    return components;
  }

  public void setComponents( List<ProductComponentItem> components )
  {
    this.components = components;
  }

  public SubscriptionMetafields getMetafields()
  {
    return metafields;
  }

  public void setMetafields( SubscriptionMetafields metafields )
  {
    this.metafields = metafields;
  }

  public String getReference()
  {
    return reference;
  }

  public SubscriptionInput setReference( String reference )
  {
    this.reference = reference;
    return this;
  }
}
