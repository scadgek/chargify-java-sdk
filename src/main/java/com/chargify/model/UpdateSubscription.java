package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
@JsonInclude( JsonInclude.Include.NON_NULL )
public class UpdateSubscription implements Serializable
{

  @JsonProperty( "product_handle" )
  private String productHandle;

  @JsonProperty( "product_id" )
  private String productId;

  @JsonProperty( "reference" )
  private String reference;

  @JsonProperty( "next_billing_at" )
  private String nextBillingAt;
}
