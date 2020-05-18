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
public class Component implements Serializable
{
  private String id;

  private String name;

  @JsonProperty( "pricing_scheme" )
  private String pricingScheme;

  @JsonProperty( "unit_name" )
  private String unitName;

  @JsonProperty( "unit_price" )
  private Double unitPrice;

  @JsonProperty( "product_family_id" )
  private String productFamilyId;

  @JsonProperty( "default_price_point_name" )
  private String defaultPricePointName;

  @JsonProperty( "default_price_point_handle" )
  private String defaultPricePointHandle;

  private Price[] prices;

  @JsonProperty( "price_points" )
  private PricePoint[] pricePoints;

  private ComponentKind kind;

  private Boolean archived;

  private Boolean taxable;

  private Boolean recurring;

  private String description;
}
