package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude( JsonInclude.Include.NON_NULL )
public class Charge
{
  private String id;

  @JsonProperty( "subscription_id" )
  private String subscriptionId;

  private String type;

  private String kind;

  @JsonProperty( "transaction_type" )
  private String transactionType;

  private Boolean success;

  @JsonProperty( "amount_in_cents" )
  private int amountInCents;

  private String memo;

  @JsonProperty( "created_at" )
  private Date createdAt;

  @JsonProperty( "starting_balance_in_cents" )
  private long startingBalanceInCents;

  @JsonProperty( "ending_balance_in_cents" )
  private long endingBalanceInCents;

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

  @JsonProperty( "tax_id" )
  private String taxId;

  @JsonProperty( "component_id" )
  private String componentId;

  @JsonProperty( "statement_id" )
  private String statementId;

  @JsonProperty( "customer_id" )
  private String customerId;

  @JsonProperty( "original_amount_in_cents" )
  private int originalAmountInCents;

  @JsonProperty( "discount_amount_in_cents" )
  private int discountAmountInCents;

  @JsonProperty( "taxable_amount_in_cents" )
  private int taxableAmountInCents;

  private String[] taxations;
}
