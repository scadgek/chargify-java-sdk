package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Date;

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

  public Charge()
  {
  }

  public String getId()
  {
    return id;
  }

  public void setId( String id )
  {
    this.id = id;
  }

  public String getSubscriptionId()
  {
    return subscriptionId;
  }

  public void setSubscriptionId( String subscriptionId )
  {
    this.subscriptionId = subscriptionId;
  }

  public String getType()
  {
    return type;
  }

  public void setType( String type )
  {
    this.type = type;
  }

  public String getKind()
  {
    return kind;
  }

  public void setKind( String kind )
  {
    this.kind = kind;
  }

  public String getTransactionType()
  {
    return transactionType;
  }

  public void setTransactionType( String transactionType )
  {
    this.transactionType = transactionType;
  }

  public Boolean getSuccess()
  {
    return success;
  }

  public void setSuccess( Boolean success )
  {
    this.success = success;
  }

  public int getAmountInCents()
  {
    return amountInCents;
  }

  public void setAmountInCents( int amountInCents )
  {
    this.amountInCents = amountInCents;
  }

  public String getMemo()
  {
    return memo;
  }

  public void setMemo( String memo )
  {
    this.memo = memo;
  }

  public Date getCreatedAt()
  {
    return createdAt;
  }

  public void setCreatedAt( Date createdAt )
  {
    this.createdAt = createdAt;
  }

  public int getStartingBalanceInCents()
  {
    return startingBalanceInCents;
  }

  public void setStartingBalanceInCents( int startingBalanceInCents )
  {
    this.startingBalanceInCents = startingBalanceInCents;
  }

  public int getEndingBalanceInCents()
  {
    return endingBalanceInCents;
  }

  public void setEndingBalanceInCents( int endingBalanceInCents )
  {
    this.endingBalanceInCents = endingBalanceInCents;
  }

  public String getGatewayUsed()
  {
    return gatewayUsed;
  }

  public void setGatewayUsed( String gatewayUsed )
  {
    this.gatewayUsed = gatewayUsed;
  }

  public String getGatewayTransactionId()
  {
    return gatewayTransactionId;
  }

  public void setGatewayTransactionId( String gatewayTransactionId )
  {
    this.gatewayTransactionId = gatewayTransactionId;
  }

  public String getGatewayOrderId()
  {
    return gatewayOrderId;
  }

  public void setGatewayOrderId( String gatewayOrderId )
  {
    this.gatewayOrderId = gatewayOrderId;
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

  public String getTaxId()
  {
    return taxId;
  }

  public void setTaxId( String taxId )
  {
    this.taxId = taxId;
  }

  public String getComponentId()
  {
    return componentId;
  }

  public void setComponentId( String componentId )
  {
    this.componentId = componentId;
  }

  public String getStatementId()
  {
    return statementId;
  }

  public void setStatementId( String statementId )
  {
    this.statementId = statementId;
  }

  public String getCustomerId()
  {
    return customerId;
  }

  public void setCustomerId( String customerId )
  {
    this.customerId = customerId;
  }

  public int getOriginalAmountInCents()
  {
    return originalAmountInCents;
  }

  public void setOriginalAmountInCents( int originalAmountInCents )
  {
    this.originalAmountInCents = originalAmountInCents;
  }

  public int getDiscountAmountInCents()
  {
    return discountAmountInCents;
  }

  public void setDiscountAmountInCents( int discountAmountInCents )
  {
    this.discountAmountInCents = discountAmountInCents;
  }

  public int getTaxableAmountInCents()
  {
    return taxableAmountInCents;
  }

  public void setTaxableAmountInCents( int taxableAmountInCents )
  {
    this.taxableAmountInCents = taxableAmountInCents;
  }

  public String[] getTaxations()
  {
    return taxations;
  }

  public void setTaxations( String[] taxations )
  {
    this.taxations = taxations;
  }

  @Override
  public String toString()
  {
    return "Charge{" +
            "id='" + id + '\'' +
            ", subscriptionId='" + subscriptionId + '\'' +
            ", type='" + type + '\'' +
            ", kind='" + kind + '\'' +
            ", transactionType='" + transactionType + '\'' +
            ", success=" + success +
            ", amountInCents=" + amountInCents +
            ", memo='" + memo + '\'' +
            ", createdAt=" + createdAt +
            ", startingBalanceInCents=" + startingBalanceInCents +
            ", endingBalanceInCents=" + endingBalanceInCents +
            ", gatewayUsed='" + gatewayUsed + '\'' +
            ", gatewayTransactionId='" + gatewayTransactionId + '\'' +
            ", gatewayOrderId='" + gatewayOrderId + '\'' +
            ", paymentId='" + paymentId + '\'' +
            ", productId='" + productId + '\'' +
            ", taxId='" + taxId + '\'' +
            ", componentId='" + componentId + '\'' +
            ", statementId='" + statementId + '\'' +
            ", customerId='" + customerId + '\'' +
            ", originalAmountInCents=" + originalAmountInCents +
            ", discountAmountInCents=" + discountAmountInCents +
            ", taxableAmountInCents=" + taxableAmountInCents +
            ", taxations=" + Arrays.toString( taxations ) + '}';
  }
}
