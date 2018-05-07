/*
 * ********************************************************************************************************************
 *  <p/>
 *  BACKENDLESS.COM CONFIDENTIAL
 *  <p/>
 *  ********************************************************************************************************************
 *  <p/>
 *  Copyright 2012 BACKENDLESS.COM. All Rights Reserved.
 *  <p/>
 *  NOTICE:  All information contained herein is, and remains the property of Backendless.com and its suppliers,
 *  if any.  The intellectual and technical concepts contained herein are proprietary to Backendless.com and its
 *  suppliers and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret
 *  or copyright law. Dissemination of this information or reproduction of this material is strictly forbidden
 *  unless prior written permission is obtained from Backendless.com.
 *  <p/>
 *  ********************************************************************************************************************
 */

package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonInclude( JsonInclude.Include.NON_NULL )
public class SubscriptionComponent implements Serializable
{
  @JsonProperty( "component_id" )
  private String componentId;

  @JsonProperty( "subscription_id" )
  private String subscriptionId;

  @JsonProperty( "unit_balance" )
  private Integer unitBalance;

  @JsonProperty( "pricing_scheme" )
  private String pricingScheme;

  private String name;

  private ComponentKind kind;

  @JsonProperty( "unit_name" )
  private String unitName;

  @JsonProperty( "enabled" )
  private Boolean enabled;

  @JsonProperty( "allocated_quantity" )
  private Integer allocatedQuantity;

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

  public Integer getUnitBalance()
  {
    return unitBalance;
  }

  public void setUnitBalance( Integer unitBalance )
  {
    this.unitBalance = unitBalance;
  }

  public String getPricingScheme()
  {
    return pricingScheme;
  }

  public void setPricingScheme( String pricingScheme )
  {
    this.pricingScheme = pricingScheme;
  }

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public ComponentKind getKind()
  {
    return kind;
  }

  public void setKind( ComponentKind kind )
  {
    this.kind = kind;
  }

  public String getUnitName()
  {
    return unitName;
  }

  public void setUnitName( String unitName )
  {
    this.unitName = unitName;
  }

  public Boolean getEnabled()
  {
    return enabled;
  }

  public void setEnabled( Boolean enabled )
  {
    this.enabled = enabled;
  }

  public Integer getAllocatedQuantity()
  {
    return allocatedQuantity;
  }

  public void setAllocatedQuantity( Integer allocatedQuantity )
  {
    this.allocatedQuantity = allocatedQuantity;
  }
}
