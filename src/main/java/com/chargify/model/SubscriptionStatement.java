package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties( ignoreUnknown = true )
@Data
@JsonInclude( JsonInclude.Include.NON_NULL )
public class SubscriptionStatement implements Serializable
{
  @JsonProperty( "id" )
  private Integer id;
  @JsonProperty( "created_at" )
  private LocalDateTime closedAt;
  @JsonProperty( "opened_at" )
  private LocalDateTime openedAt;
  @JsonProperty( "settled_at" )
  private LocalDateTime settledAt;
  @JsonProperty( "created_at" )
  private LocalDateTime createdAt;
  @JsonProperty( "updated_at" )
  private LocalDateTime updatedAt;
  @JsonProperty( "customer_first_name" )
  private String customerFirstName;
  @JsonProperty( "customer_last_name" )
  private String customerLastName;
  @JsonProperty( "customer_billing_address" )
  private String customerBillingAddress;
  @JsonProperty( "customer_billing_address_2" )
  private String customerBillingAddress2;
  @JsonProperty( "customer_billing_city" )
  private String customerBillingCity;
  @JsonProperty( "customer_billing_country" )
  private String customerBillingCountry;
  @JsonProperty( "customer_billing_state" )
  private String customerBillingState;
  @JsonProperty( "customer_billing_zip" )
  private String customerBillingZip;
  @JsonProperty( "customer_organization" )
  private String customerOrganization;
  @JsonProperty( "customer_shipping_address" )
  private String customerShippingAddress;
  @JsonProperty( "customer_shipping_address_2" )
  private String customerShippingAddress2;
  @JsonProperty( "customer_shipping_city" )
  private String customerShippingCity;
  @JsonProperty( "customer_shipping_country" )
  private String customerShippingCountry;
  @JsonProperty( "customer_shipping_state" )
  private String customerShippingState;
  @JsonProperty( "customer_shipping_zip" )
  private String customerShippingZip;
  @JsonProperty( "starting_balance_in_cents" )
  private Integer startingBalanceInCents;
  @JsonProperty( "ending_balance_in_cents" )
  private Integer endingBalanceInCents;
  @JsonProperty( "memo" )
  private String memo;
  @JsonProperty( "subscription_id" )
  private Integer subscriptionId;
  @JsonProperty( "total_in_cents" )
  private Integer totalInCents;
  @JsonDeserialize(contentAs = Transaction.class)
  private List<Transaction> transactions;

}
