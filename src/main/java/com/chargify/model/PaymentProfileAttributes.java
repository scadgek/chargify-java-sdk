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

@JsonIgnoreProperties( ignoreUnknown = true )
@JsonInclude( JsonInclude.Include.NON_NULL )
public class PaymentProfileAttributes
{
  @JsonProperty( "first_name" )
  private String firstName;

  @JsonProperty( "last_name" )
  private String lastName;

  @JsonProperty( "full_number" )
  private String fullNumber;

  @JsonProperty( "expiration_month" )
  private String expirationMonth;

  @JsonProperty( "expiration_year" )
  private String expirationYear;

  private String cvv;

  @JsonProperty( "billing_address" )
  private String billingAddress;

  @JsonProperty( "billing_address_2" )
  private String billingAddress2;

  @JsonProperty( "billing_city" )
  private String billingCity;

  @JsonProperty( "billing_state" )
  private String billingState;

  @JsonProperty( "billing_zip" )
  private String billingZip;

  @JsonProperty( "billing_country" )
  private String billingCountry;

  @JsonProperty( "vault_token" )
  private String vaultToken;

  @JsonProperty( "customer_vault_token" )
  private String customerVaultToken;

  @JsonProperty( "current_vault" )
  private String currentVault;

  @JsonProperty( "last_four" )
  private String lastFour;

  @JsonProperty( "card_type" )
  private String cardType;

  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName( String firstName )
  {
    this.firstName = firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setLastName( String lastName )
  {
    this.lastName = lastName;
  }

  public String getFullNumber()
  {
    return fullNumber;
  }

  public void setFullNumber( String fullNumber )
  {
    this.fullNumber = fullNumber;
  }

  public String getExpirationMonth()
  {
    return expirationMonth;
  }

  public void setExpirationMonth( String expirationMonth )
  {
    this.expirationMonth = expirationMonth;
  }

  public String getExpirationYear()
  {
    return expirationYear;
  }

  public void setExpirationYear( String expirationYear )
  {
    this.expirationYear = expirationYear;
  }

  public String getCvv()
  {
    return cvv;
  }

  public void setCvv( String cvv )
  {
    this.cvv = cvv;
  }

  public String getBillingAddress()
  {
    return billingAddress;
  }

  public void setBillingAddress( String billingAddress )
  {
    this.billingAddress = billingAddress;
  }

  public String getBillingAddress2()
  {
    return billingAddress2;
  }

  public void setBillingAddress2( String billingAddress2 )
  {
    this.billingAddress2 = billingAddress2;
  }

  public String getBillingCity()
  {
    return billingCity;
  }

  public void setBillingCity( String billingCity )
  {
    this.billingCity = billingCity;
  }

  public String getBillingState()
  {
    return billingState;
  }

  public void setBillingState( String billingState )
  {
    this.billingState = billingState;
  }

  public String getBillingZip()
  {
    return billingZip;
  }

  public void setBillingZip( String billingZip )
  {
    this.billingZip = billingZip;
  }

  public String getBillingCountry()
  {
    return billingCountry;
  }

  public void setBillingCountry( String billingCountry )
  {
    this.billingCountry = billingCountry;
  }

  public String getVaultToken()
  {
    return vaultToken;
  }

  public void setVaultToken( String vaultToken )
  {
    this.vaultToken = vaultToken;
  }

  public String getCustomerVaultToken()
  {
    return customerVaultToken;
  }

  public void setCustomerVaultToken( String customerVaultToken )
  {
    this.customerVaultToken = customerVaultToken;
  }

  public String getCurrentVault()
  {
    return currentVault;
  }

  public void setCurrentVault( String currentVault )
  {
    this.currentVault = currentVault;
  }

  public String getLastFour()
  {
    return lastFour;
  }

  public void setLastFour( String lastFour )
  {
    this.lastFour = lastFour;
  }

  public String getCardType()
  {
    return cardType;
  }

  public void setCardType( String cardType )
  {
    this.cardType = cardType;
  }
}
