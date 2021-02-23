package com.chargify.model.product;

import com.chargify.model.PricePointIntervalUnit;
import com.chargify.model.PublicSignupPage;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude( JsonInclude.Include.NON_NULL )
public class Product implements Serializable
{
  private String id;

  private String name;

  private String handle;

  private String description;

  @JsonProperty( "accounting_code" )
  private String accountingCode;

  @JsonProperty( "request_credit_card" )
  private Boolean requestCreditCard;

  @JsonProperty( "expiration_interval" )
  private String expirationInterval;

  @JsonProperty( "expiration_interval_unit" )
  private String expirationIntervalUnit;

  @JsonProperty( "created_at" )
  private Date createdAt;

  @JsonProperty( "updated_at" )
  private Date updatedAt;

  @JsonProperty( "price_in_cents" )
  private int priceInCents;

  @JsonProperty( "interval" )
  private int recurringInterval;

  @JsonProperty( "interval_unit" )
  private PricePointIntervalUnit intervalUnit;

  @JsonProperty( "initial_charge_in_cents" )
  private String initialChargeInCents;

  @JsonProperty( "trial_price_in_cents" )
  private Integer trialPriceInCents;

  @JsonProperty( "trial_interval" )
  private Integer trialInterval;

  @JsonProperty( "trial_interval_unit" )
  private PricePointIntervalUnit trialIntervalUnit;

  @JsonProperty( "archived_at" )
  private Date archivedAt;

  @JsonProperty( "require_credit_card" )
  private Boolean requireCreditCard;

  @JsonProperty( "return_params" )
  private String returnParams;

  @JsonProperty( "product_price_point_name" )
  private String productPricePointName;

  @JsonProperty( "product_price_point_handle" )
  private String productPricePointHandle;

  private Boolean taxable;

  @JsonProperty( "update_return_url" )
  private String updateReturnUrl;

  @JsonProperty( "initial_charge_after_trial" )
  private Boolean initialChargeAfterTrial;

  @JsonProperty( "version_number" )
  private Integer versionNumber;

  @JsonProperty( "update_return_params" )
  private String updateReturnParams;

  @JsonProperty( "product_family" )
  private ProductFamily productFamily;

  @JsonProperty( "public_signup_pages" )
  private List<PublicSignupPage> publicSignupPages;

  @JsonCreator
  public Product( @JsonProperty( "name" ) final String name,
                  @JsonProperty( "price" ) final int priceInCents,
                  @JsonProperty( "interval" ) final int recurringInterval,
                  @JsonProperty( "interval_unit" ) final PricePointIntervalUnit intervalUnit )
  {
    this.name = name;
    this.priceInCents = priceInCents;
    this.recurringInterval = recurringInterval;
    this.intervalUnit = intervalUnit;
  }
}
