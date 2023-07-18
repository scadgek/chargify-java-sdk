package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

@ToString
@Getter
@Setter
@JsonInclude( JsonInclude.Include.NON_NULL )
public class Allocation
{
  @JsonProperty( "component_id" )
  private String componentId;

  @JsonProperty( "subscription_id" )
  private String subscriptionId;

  private Integer quantity;

  @JsonProperty( "previous_quantity" )
  private Integer previousQuantity;

  private String memo;

  private ZonedDateTime timestamp;

  @JsonProperty( "upgrade_charge" )
  private String upgradeCharge;

  @JsonProperty( "downgrade_credit" )
  private String downgradeCredit;

  private Payment payment;
}
