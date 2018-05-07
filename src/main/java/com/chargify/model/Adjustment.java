package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties( ignoreUnknown = true )
@JsonInclude( JsonInclude.Include.NON_NULL )
public class Adjustment
{
  private String id;
  private Boolean success;
  private String memo;

  @JsonProperty( "amount_in_cents" )
  private Integer amount_in_cents;

  @JsonProperty( "ending_balance_in_cents" )
  private Integer ending_balance_in_cents;
  private String type;

  @JsonProperty( "transaction_type" )
  private String transaction_type;

  @JsonProperty( "subscription_id" )
  private String subscription_id;

  @JsonProperty( "product_id" )
  private String product_id;

  @JsonProperty( "created_at" )
  private Date created_at;

  @JsonProperty( "payment_id" )
  private String payment_id;

  public Adjustment()
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

  public Boolean getSuccess()
  {
    return success;
  }

  public void setSuccess( Boolean success )
  {
    this.success = success;
  }

  public String getMemo()
  {
    return memo;
  }

  public void setMemo( String memo )
  {
    this.memo = memo;
  }

  public Integer getAmount_in_cents()
  {
    return amount_in_cents;
  }

  public void setAmount_in_cents( Integer amount_in_cents )
  {
    this.amount_in_cents = amount_in_cents;
  }

  public Integer getEnding_balance_in_cents()
  {
    return ending_balance_in_cents;
  }

  public void setEnding_balance_in_cents( Integer ending_balance_in_cents )
  {
    this.ending_balance_in_cents = ending_balance_in_cents;
  }

  public String getType()
  {
    return type;
  }

  public void setType( String type )
  {
    this.type = type;
  }

  public String getTransaction_type()
  {
    return transaction_type;
  }

  public void setTransaction_type( String transaction_type )
  {
    this.transaction_type = transaction_type;
  }

  public String getSubscription_id()
  {
    return subscription_id;
  }

  public void setSubscription_id( String subscription_id )
  {
    this.subscription_id = subscription_id;
  }

  public String getProduct_id()
  {
    return product_id;
  }

  public void setProduct_id( String product_id )
  {
    this.product_id = product_id;
  }

  public Date getCreated_at()
  {
    return created_at;
  }

  public void setCreated_at( Date created_at )
  {
    this.created_at = created_at;
  }

  public String getPayment_id()
  {
    return payment_id;
  }

  public void setPayment_id( String payment_id )
  {
    this.payment_id = payment_id;
  }

  @Override
  public String toString()
  {
    return "Adjustment{" +
            "id='" + id + '\'' +
            ", success=" + success +
            ", memo='" + memo + '\'' +
            ", amount_in_cents=" + amount_in_cents +
            ", ending_balance_in_cents=" + ending_balance_in_cents +
            ", type='" + type + '\'' +
            ", transaction_type='" + transaction_type + '\'' +
            ", subscription_id='" + subscription_id + '\'' +
            ", product_id='" + product_id + '\'' +
            ", created_at=" + created_at +
            ", payment_id='" + payment_id + '\'' + '}';
  }
}
