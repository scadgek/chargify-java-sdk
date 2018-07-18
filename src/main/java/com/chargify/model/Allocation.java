package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

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

  private Date timestamp;

  @JsonProperty( "proration_upgrade_scheme" )
  private String prorationUpgradeScheme;

  @JsonProperty( "proration_downgrade_scheme" )
  private String prorationDowngradeScheme;

  private Payment payment;

  public String getComponentId()
  {
    return componentId;
  }

  public void setComponentId( String componentId )
  {
    this.componentId = componentId;
  }

  public String getSubscriptionId()
  {
    return subscriptionId;
  }

  public void setSubscriptionId( String subscriptionId )
  {
    this.subscriptionId = subscriptionId;
  }

  public Integer getQuantity()
  {
    return quantity;
  }

  public void setQuantity( Integer quantity )
  {
    this.quantity = quantity;
  }

  public Integer getPreviousQuantity()
  {
    return previousQuantity;
  }

  public void setPreviousQuantity( Integer previousQuantity )
  {
    this.previousQuantity = previousQuantity;
  }

  public String getMemo()
  {
    return memo;
  }

  public void setMemo( String memo )
  {
    this.memo = memo;
  }

  public Date getTimestamp()
  {
    return timestamp;
  }

  public void setTimestamp( Date timestamp )
  {
    this.timestamp = timestamp;
  }

  public String getProrationUpgradeScheme()
  {
    return prorationUpgradeScheme;
  }

  public void setProrationUpgradeScheme( String prorationUpgradeScheme )
  {
    this.prorationUpgradeScheme = prorationUpgradeScheme;
  }

  public String getProrationDowngradeScheme()
  {
    return prorationDowngradeScheme;
  }

  public void setProrationDowngradeScheme( String prorationDowngradeScheme )
  {
    this.prorationDowngradeScheme = prorationDowngradeScheme;
  }

  public Payment getPayment()
  {
    return payment;
  }

  public void setPayment( Payment payment )
  {
    this.payment = payment;
  }

  @Override
  public String toString()
  {
    return "Allocation{" +
            "componentId='" + componentId + '\'' +
            ", subscriptionId='" + subscriptionId + '\'' +
            ", quantity=" + quantity +
            ", previousQuantity=" + previousQuantity +
            ", memo='" + memo + '\'' +
            ", timestamp=" + timestamp +
            ", prorationUpgradeScheme='" + prorationUpgradeScheme + '\'' +
            ", prorationDowngradeScheme='" + prorationDowngradeScheme + '\'' +
            ", payment=" + payment +
            '}';
  }
}
