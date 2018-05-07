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
public class ComponentDefinition implements Serializable
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

  private ComponentKind kind;

  private Boolean archived;

  private Boolean taxable;

  private String description;

  public String getId()
  {
    return id;
  }

  public void setId( String id )
  {
    this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public String getPricingScheme()
  {
    return pricingScheme;
  }

  public void setPricingScheme( String pricingScheme )
  {
    this.pricingScheme = pricingScheme;
  }

  public String getUnitName()
  {
    return unitName;
  }

  public void setUnitName( String unitName )
  {
    this.unitName = unitName;
  }

  public Double getUnitPrice()
  {
    return unitPrice;
  }

  public void setUnitPrice( Double unitPrice )
  {
    this.unitPrice = unitPrice;
  }

  public String getProductFamilyId()
  {
    return productFamilyId;
  }

  public void setProductFamilyId( String productFamilyId )
  {
    this.productFamilyId = productFamilyId;
  }

  public ComponentKind getKind()
  {
    return kind;
  }

  public void setKind( ComponentKind kind )
  {
    this.kind = kind;
  }

  public Boolean getArchived()
  {
    return archived;
  }

  public void setArchived( Boolean archived )
  {
    this.archived = archived;
  }

  public Boolean getTaxable()
  {
    return taxable;
  }

  public void setTaxable( Boolean taxable )
  {
    this.taxable = taxable;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription( String description )
  {
    this.description = description;
  }
}
