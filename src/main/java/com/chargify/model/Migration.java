package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor( access = AccessLevel.PRIVATE )
@NoArgsConstructor
@Data
@JsonInclude( JsonInclude.Include.NON_NULL )
public class Migration
{
  @JsonProperty( "product_handle" )
  private String productHandle;

  @JsonProperty( "include_trial")
  private Boolean includeTrial;

  @JsonProperty( "preserve_period")
  private Boolean preservePeriod;

  @JsonProperty( "product_price_point_handle")
  private String pricePointHandle;
}
