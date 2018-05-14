package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude( JsonInclude.Include.NON_NULL )
public class ReferralCode
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

  public ReferralCode setId( String id )
  {
    this.id = id;
    return this;
  }

  public String getSiteId()
  {
    return siteId;
  }

  public ReferralCode setSiteId( String siteId )
  {
    this.siteId = siteId;
    return this;
  }

  public String getSubscriptionId()
  {
    return subscriptionId;
  }

  public ReferralCode setSubscriptionId( String subscriptionId )
  {
    this.subscriptionId = subscriptionId;
    return this;
  }

  public String getCode()
  {
    return code;
  }

  public ReferralCode setCode( String code )
  {
    this.code = code;
    return this;
  }
}
