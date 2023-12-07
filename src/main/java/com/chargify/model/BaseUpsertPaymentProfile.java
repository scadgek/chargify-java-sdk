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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonIgnoreProperties( ignoreUnknown = true )
@JsonInclude( JsonInclude.Include.NON_NULL )
public abstract class BaseUpsertPaymentProfile
{
  @JsonProperty( "first_name" )
  private String firstName;

  @JsonProperty( "last_name" )
  private String lastName;

  @JsonProperty( "payment_type" )
  private String paymentType;

  @JsonProperty( "full_number" )
  private String cardNumber;

  @JsonProperty( "expiration_month" )
  private Integer expirationMonth;

  @JsonProperty( "expiration_year" )
  private Integer expirationYear;

  @JsonProperty( "billing_zip" )
  private String billingZip;

  private String cvv;
}
