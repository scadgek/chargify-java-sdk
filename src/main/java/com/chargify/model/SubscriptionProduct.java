package com.chargify.model;

import com.chargify.model.product.BaseProduct;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@EqualsAndHashCode( callSuper = true )
@JsonInclude( JsonInclude.Include.NON_NULL )
public class SubscriptionProduct extends BaseProduct implements Serializable
{
  @JsonProperty( "archived_at" )
  private Date archivedAt;
  @JsonProperty( "product_price_point_handle" )
  private String productPricePointHandle;

  @JsonCreator
  public SubscriptionProduct( @JsonProperty( "name" ) final String name,
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
