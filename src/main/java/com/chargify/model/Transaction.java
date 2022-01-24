package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties( ignoreUnknown = true )
@JsonInclude( JsonInclude.Include.NON_NULL )
public class Transaction
{
  private Integer id;
  @JsonProperty( "subscription_id" )
  private String subscriptionId;
  private String type;
  private String kind;
  @JsonProperty( "transaction_type" )
  private String transactionType;
  private boolean success;
  @JsonProperty( "amount_in_cents" )
  private int amountInCents;
  private String memo;
  @JsonProperty( "created_at" )
  private LocalDateTime createdAt;
  @JsonProperty( "starting_balance_in_cents" )
  private int startingBalanceInCents;
  @JsonProperty( "ending_balance_in_cents" )
  private int endingBalanceInCents;
  @JsonProperty( "gateway_used" )
  private String gatewayUsed;
  @JsonProperty( "gateway_transaction_id" )
  private String gatewayTransactionId;
  @JsonProperty( "gateway_order_id" )
  private String gatewayOrderId;
  @JsonProperty( "payment_id" )
  private String paymentId;
  @JsonProperty( "product_id" )
  private String productId;
}
