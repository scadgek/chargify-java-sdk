package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@JsonInclude( JsonInclude.Include.NON_NULL )
@NoArgsConstructor
@Data
@Accessors( chain = true )
public class ReferralCode
{
  private String id;

  @JsonProperty( "site_id" )
  private String siteId;

  @JsonProperty( "subscription_id" )
  private String subscriptionId;

  private String code;
}
