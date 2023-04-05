package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@JsonInclude( JsonInclude.Include.NON_NULL )
@Data
public final class Price implements Serializable
{
  private Integer id;
  @JsonProperty( "starting_quantity" )
  private Integer startingQuantity;
  @JsonProperty( "ending_quantity" )
  private Integer endingQuantity;
  @JsonProperty( "unit_price" )
  private Double unitPrice;

  @JsonCreator
  public Price()
  {
  }
}
