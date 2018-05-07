package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties( ignoreUnknown = true )
@JsonInclude( JsonInclude.Include.NON_NULL )
public class Transaction
{
  @JsonProperty( "amount_in_cents" )
  private int amountInCents;

  @JsonProperty( "created_at" )
  private Date createdAt;

  @JsonProperty( "starting_balance_in_cents" )
  private int startingBalanceInCents;

  @JsonProperty( "ending_balance_in_cents" )
  private int endingBalanceInCents;

  private String id;

  private String kind;

  private String memo;

  @JsonProperty( "payment_id" )
  private String paymentId;

  @JsonProperty( "product_id" )
  private String productId;

  @JsonProperty( "subscription_id" )
  private String subscriptionId;

  private boolean success;

  private String type;

  @JsonProperty( "transaction_type" )
  private String transactionType;

  @JsonProperty( "gateway_transaction_id" )
  private String gatewayTransactionId;

  public int getAmountInCents()
  {
    return amountInCents;
  }

  public void setAmountInCents( int amountInCents )
  {
    this.amountInCents = amountInCents;
  }

  public Date getCreatedAt()
  {
    return createdAt;
  }

  public void setCreatedAt( Date createdAt )
  {
    this.createdAt = createdAt;
  }

  public int getEndingBalanceInCents()
  {
    return endingBalanceInCents;
  }

  public void setEndingBalanceInCents( int endingBalanceInCents )
  {
    this.endingBalanceInCents = endingBalanceInCents;
  }

  public String getId()
  {
    return id;
  }

  public void setId( String id )
  {
    this.id = id;
  }

  public String getKind()
  {
    return kind;
  }

  public void setKind( String kind )
  {
    this.kind = kind;
  }

  public String getMemo()
  {
    return memo;
  }

  public void setMemo( String memo )
  {
    this.memo = memo;
  }

  public String getPaymentId()
  {
    return paymentId;
  }

  public void setPaymentId( String paymentId )
  {
    this.paymentId = paymentId;
  }

  public String getProductId()
  {
    return productId;
  }

  public void setProductId( String productId )
  {
    this.productId = productId;
  }

  public int getStartingBalanceInCents()
  {
    return startingBalanceInCents;
  }

  public void setStartingBalanceInCents( int startingBalanceInCents )
  {
    this.startingBalanceInCents = startingBalanceInCents;
  }

  public String getSubscriptionId()
  {
    return subscriptionId;
  }

  public void setSubscriptionId( String subscriptionId )
  {
    this.subscriptionId = subscriptionId;
  }

  public boolean getSuccess()
  {
    return success;
  }

  public void setSuccess( boolean success )
  {
    this.success = success;
  }

  public String getType()
  {
    return type;
  }

  public void setType( String type )
  {
    this.type = type;
  }

  public String getTransactionType()
  {
    return transactionType;
  }

  public void setTransactionType( String transactionType )
  {
    this.transactionType = transactionType;
  }

  public String getGatewayTransactionId()
  {
    return gatewayTransactionId;
  }

  public void setGatewayTransactionId( String gatewayTransactionId )
  {
    this.gatewayTransactionId = gatewayTransactionId;
  }

  @Override
  public String toString()
  {
    return "Transaction{" +
            "amountInCents=" + amountInCents +
            ", createdAt=" + createdAt +
            ", startingBalanceInCents=" + startingBalanceInCents +
            ", endingBalanceInCents=" + endingBalanceInCents +
            ", id='" + id + '\'' +
            ", kind='" + kind + '\'' +
            ", memo='" + memo + '\'' +
            ", paymentId='" + paymentId + '\'' +
            ", productId='" + productId + '\'' +
            ", subscriptionId='" + subscriptionId + '\'' +
            ", success=" + success +
            ", type='" + type + '\'' +
            ", transactionType='" + transactionType + '\'' +
            ", gatewayTransactionId='" + gatewayTransactionId + '\'' + '}';
  }
}
