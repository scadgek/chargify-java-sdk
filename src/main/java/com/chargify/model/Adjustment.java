package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.ZonedDateTime;

@JsonIgnoreProperties( ignoreUnknown = true )
@JsonInclude( JsonInclude.Include.NON_NULL )
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class Adjustment
{
  private String id;
  private Boolean success;
  private String memo;

  @JsonProperty( "amount_in_cents" )
  private Long amountInCents;

  @JsonProperty( "ending_balance_in_cents" )
  private Long endingBalanceInCents;
  private String type;

  @JsonProperty( "transaction_type" )
  private String transactionType;

  @JsonProperty( "subscription_id" )
  private String subscriptionId;

  @JsonProperty( "product_id" )
  private String productId;

  @JsonProperty( "created_at" )
  private ZonedDateTime createdAt;

  @JsonProperty( "payment_id" )
  private String paymentId;
}
