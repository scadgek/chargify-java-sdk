package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode( callSuper = true )
@JsonInclude( JsonInclude.Include.NON_NULL )
public class Product extends BaseProduct implements Serializable
{
  @JsonProperty( "request_billing_address" )
  private Boolean requestBillingAddress;
  @JsonProperty( "require_billing_address" )
  private Boolean requireBillingAddress;
  @JsonProperty( "require_shipping_address" )
  private Boolean requireShippingAddress;
  @JsonProperty( "default_product_price_point_id" )
  private Integer defaultProductPricePointId;

  @JsonCreator
  public Product( @JsonProperty( "name" ) final String name,
                  @JsonProperty( "price" ) final int priceInCents,
                  @JsonProperty( "interval" ) final int recurringInterval,
                  @JsonProperty( "interval_unit" ) final IntervalUnit intervalUnit )
  {
    this.name = name;
    this.priceInCents = priceInCents;
    this.recurringInterval = recurringInterval;
    this.intervalUnit = intervalUnit;
  }
}
