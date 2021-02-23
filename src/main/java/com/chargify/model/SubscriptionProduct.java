package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode( callSuper = true )
@JsonInclude( JsonInclude.Include.NON_NULL )
public class SubscriptionProduct extends BaseProduct implements Serializable
{
  @JsonProperty( "archived_at" )
  private Date archivedAt;

  @JsonCreator
  public SubscriptionProduct( @JsonProperty( "name" ) final String name,
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
