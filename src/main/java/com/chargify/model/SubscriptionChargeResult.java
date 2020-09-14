package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties( ignoreUnknown = true )
@JsonInclude( JsonInclude.Include.NON_NULL )
public class SubscriptionChargeResult implements Serializable
{
  private Integer id;
  @JsonProperty( "subscription_id" )
  private Integer subscriptionId;
  private String type;
  private String kind;
  @JsonProperty( "transaction_type" )
  private String transactionType;
  private Boolean success;
  @JsonProperty( "amount_in_cents" )
  private Integer amountInCents;
  private String memo;
  @JsonProperty( "created_at" )
  private String createdAt;
  @JsonProperty( "starting_balance_in_cents" )
  private Integer startingBalanceInCents;
  @JsonProperty( "ending_balance_in_cents" )
  private Integer endingBalanceInCents;
  @JsonProperty( "product_id" )
  private Integer productId;
  @JsonProperty( "statement_id" )
  private Integer statementId;
  @JsonProperty( "customer_id" )
  private Integer customerId;
  @JsonProperty( "period_range_start" )
  private String periodRangeStart;
  @JsonProperty( "period_range_end" )
  private String periodRangeEnd;

}
