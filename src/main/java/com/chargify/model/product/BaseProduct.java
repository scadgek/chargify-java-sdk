package com.chargify.model.product;

import com.chargify.model.PricePointIntervalUnit;
import com.chargify.model.PublicSignupPage;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
@ToString
@JsonInclude( JsonInclude.Include.NON_NULL )
public abstract class BaseProduct implements Serializable
{
  protected String id;

  protected String name;

  protected String handle;

  protected String description;

  @JsonProperty( "accounting_code" )
  protected String accountingCode;

  @JsonProperty( "request_credit_card" )
  protected Boolean requestCreditCard;

  @JsonProperty( "expiration_interval" )
  protected String expirationInterval;

  @JsonProperty( "expiration_interval_unit" )
  protected String expirationIntervalUnit;

  @JsonProperty( "created_at" )
  protected Date createdAt;

  @JsonProperty( "updated_at" )
  protected Date updatedAt;

  @JsonProperty( "price_in_cents" )
  protected int priceInCents;

  @JsonProperty( "interval" )
  protected int recurringInterval;

  @JsonProperty( "interval_unit" )
  protected PricePointIntervalUnit intervalUnit;

  @JsonProperty( "initial_charge_in_cents" )
  protected String initialChargeInCents;

  @JsonProperty( "trial_price_in_cents" )
  protected Integer trialPriceInCents;

  @JsonProperty( "trial_interval" )
  protected Integer trialInterval;

  @JsonProperty( "trial_interval_unit" )
  protected String trialIntervalUnit;

  @JsonProperty( "require_credit_card" )
  protected Boolean requireCreditCard;

  @JsonProperty( "return_params" )
  protected String returnParams;

  protected Boolean taxable;

  @JsonProperty( "update_return_url" )
  protected String updateReturnUrl;

  @JsonProperty( "tax_code" )
  protected String taxCode;

  @JsonProperty( "initial_charge_after_trial" )
  protected Boolean initialChargeAfterTrial;

  @JsonProperty( "version_number" )
  protected Integer versionNumber;

  @JsonProperty( "update_return_params" )
  protected String updateReturnParams;

  @JsonProperty( "product_family" )
  protected ProductFamily productFamily;

  @JsonProperty( "public_signup_pages" )
  protected List<PublicSignupPage> publicSignupPages;

  @JsonProperty( "product_price_point_name" )
  protected String productPricePointName;
}
