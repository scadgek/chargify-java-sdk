package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude( JsonInclude.Include.NON_NULL )
public class Migration
{
  @JsonProperty( "product_handle" )
  private String productHandle;

  @JsonProperty( "include_trial")
  private Integer includeTrial;

  public String getProductHandle()
  {
    return productHandle;
  }

  public void setProductHandle( String productHandle )
  {
    this.productHandle = productHandle;
  }

  public Integer getIncludeTrial()
  {
    return includeTrial;
  }

  public void setIncludeTrial( Integer includeTrial )
  {
    this.includeTrial = includeTrial;
  }
}
