package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude( JsonInclude.Include.NON_NULL )
public class ValidationReferralCodeOutput
{
  private String id;

  @JsonProperty( "site_id" )
  private String siteId;

  @JsonProperty( "subscription_id" )
  private String subscriptionId;

  private String code;

  public String getId()
  {
    return id;
  }

  public ValidationReferralCodeOutput setId( String id )
  {
    this.id = id;
    return this;
  }

  public String getSiteId()
  {
    return siteId;
  }

  public ValidationReferralCodeOutput setSiteId( String siteId )
  {
    this.siteId = siteId;
    return this;
  }

  public String getSubscriptionId()
  {
    return subscriptionId;
  }

  public ValidationReferralCodeOutput setSubscriptionId( String subscriptionId )
  {
    this.subscriptionId = subscriptionId;
    return this;
  }

  public String getCode()
  {
    return code;
  }

  public ValidationReferralCodeOutput setCode( String code )
  {
    this.code = code;
    return this;
  }
}
