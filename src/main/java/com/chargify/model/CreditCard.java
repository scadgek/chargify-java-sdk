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
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
@JsonInclude( JsonInclude.Include.NON_NULL )
public class CreditCard implements Serializable
{
  private String id;

  @JsonProperty( "payment_type" )
  private String paymentType;

  @JsonProperty( "first_name" )
  private String firstName;

  @JsonProperty( "last_name" )
  private String lastName;

  @JsonProperty( "masked_card_number" )
  private String maskedCardNumber;

  @JsonProperty( "card_type" )
  private String cardType;

  @JsonProperty( "expiration_month" )
  private Integer expirationMonth;

  @JsonProperty( "expiration_year" )
  private Integer expirationYear;

  @JsonProperty( "billing_address" )
  private String billingAddress;

  @JsonProperty( "billing_address_2" )
  private String billingAddress2;

  @JsonProperty( "billing_city" )
  private String billingCity;

  @JsonProperty( "billing_state" )
  private String billingState;

  @JsonProperty( "billing_country" )
  private String billingCountry;

  @JsonProperty( "billing_zip" )
  private String billingZip;

  @JsonProperty( "current_vault" )
  private String currentVault;

  @JsonProperty( "vault_token" )
  private String vaultToken;

  @JsonProperty( "customer_vault_token" )
  private String customerVaultToken;

  @JsonProperty( "customer_id" )
  private String customerId;
}
