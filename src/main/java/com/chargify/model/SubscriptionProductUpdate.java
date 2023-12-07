package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor( access = AccessLevel.PRIVATE )
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
