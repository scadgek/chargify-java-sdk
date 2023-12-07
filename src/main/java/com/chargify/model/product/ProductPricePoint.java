package com.chargify.model.product;

import com.chargify.model.PricePointIntervalUnit;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonInclude( JsonInclude.Include.NON_NULL )
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductPricePoint implements Serializable
{
  private Integer id;
  private String name;
  private String handle;
  @JsonProperty( "price_in_cents" )
  private Integer priceInCents;
  private Integer interval;
  @JsonProperty( "interval_unit" )
  private PricePointIntervalUnit intervalUnit;
  @JsonProperty( "trial_price_in_cents" )
  private Integer trialPriceInCents;
  @JsonProperty( "trial_interval" )
  private Integer trialInterval;
  @JsonProperty( "trial_interval_unit" )
  private PricePointIntervalUnit trialIntervalUnit;
  @JsonProperty( "trial_type" )
  private String trialType;
  @JsonProperty( "initial_charge_in_cents" )
  private Integer initialChargeInCents;
  @JsonProperty( "initial_charge_after_trial" )
  private boolean initialChargeAfterTrial = false;
  @JsonProperty( "expiration_interval" )
  private Integer expirationInterval;
  @JsonProperty( "expiration_interval_unit" )
  private String expirationIntervalUnit;
  @JsonProperty( "product_id" )
  private String productId;
  @JsonProperty( "archived_at" )
  private String archivedAt;
  @JsonProperty( "created_at" )
  private String createdAt;
  @JsonProperty( "updated_at" )
  private String updatedAt;
  @JsonProperty( "use_site_exchange_rate" )
  private Boolean useSiteExchangeRate;

}
