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
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude( JsonInclude.Include.NON_NULL )
public class SubscriptionComponent implements Serializable
{
  @JsonProperty( "component_id" )
  private Integer componentId;

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

  @JsonProperty( "price_point_id" )
  private Integer pricePointId;

  @JsonProperty( "price_point_handle" )
  private String pricePointHandle;
}
