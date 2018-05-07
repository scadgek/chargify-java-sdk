package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude( JsonInclude.Include.NON_NULL )
public class PublicSignupPage
{
  private String id;

  @JsonProperty( "return_url" )
  private String returnUrl;

  @JsonProperty( "return_params" )
  private String returnParams;

  private String url;

  public String getId()
  {
    return id;
  }

  public void setId( String id )
  {
    this.id = id;
  }

  public String getReturnUrl()
  {
    return returnUrl;
  }

  public void setReturnUrl( String returnUrl )
  {
    this.returnUrl = returnUrl;
  }

  public String getReturnParams()
  {
    return returnParams;
  }

  public void setReturnParams( String returnParams )
  {
    this.returnParams = returnParams;
  }

  public String getUrl()
  {
    return url;
  }

  public void setUrl( String url )
  {
    this.url = url;
  }

  @Override
  public String toString()
  {
    return "PublicSignupPage{" +
            "id='" + id + '\'' +
            ", returnUrl='" + returnUrl + '\'' +
            ", returnParams='" + returnParams + '\'' +
            ", url='" + url + '\'' +
            '}';
  }
}
