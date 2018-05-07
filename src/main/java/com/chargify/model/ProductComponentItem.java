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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties( ignoreUnknown = true )
@JsonInclude( JsonInclude.Include.NON_NULL )
public class ProductComponentItem
{
  @JsonProperty( "component_id" )
  private String componentId;

  @JsonProperty( "allocated_quantity" )
  private int allocatedQuantity;

  private boolean enabled;

  public String getComponentId()
  {
    return componentId;
  }

  public void setComponentId( String componentId )
  {
    this.componentId = componentId;
  }

  public int getAllocatedQuantity()
  {
    return allocatedQuantity;
  }

  public void setAllocatedQuantity( int allocatedQuantity )
  {
    this.allocatedQuantity = allocatedQuantity;
  }

  public boolean isEnabled()
  {
    return enabled;
  }

  public void setEnabled( boolean enabled )
  {
    this.enabled = enabled;
  }
}
