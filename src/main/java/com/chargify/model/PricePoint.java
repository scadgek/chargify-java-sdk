package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonInclude( JsonInclude.Include.NON_NULL )
@NoArgsConstructor
@AllArgsConstructor
@Data
public final class PricePoint implements Serializable
{
  private Integer id;
  @JsonProperty( "default" )
  private boolean defaultPricePoint = false;
  private String name;
  private String handle;
  @JsonProperty( "pricing_scheme" )
  private String pricingScheme;
  @JsonProperty( "component_id" )
  private String componentId;
  @JsonProperty( "archived_at" )
  private String archivedAt;
  @JsonProperty( "created_at" )
  private String createdAt;
  @JsonProperty( "updated_at" )
  private String updatedAt;
  @JsonProperty( "use_site_exchange_rate" )
  private Boolean useSiteExchangeRate;
  private Price[] prices;
}
