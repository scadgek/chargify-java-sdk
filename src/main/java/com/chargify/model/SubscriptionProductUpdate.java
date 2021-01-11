package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude( JsonInclude.Include.NON_NULL )
public class SubscriptionProductUpdate implements Serializable
{

  @JsonProperty( "product_handle" )
  private String productHandle;

  @JsonProperty( "product_change_delayed" )
  private boolean changeDelayed;

  @JsonProperty( "product_price_point_handle" )
  private String pricePointHandle;
}
