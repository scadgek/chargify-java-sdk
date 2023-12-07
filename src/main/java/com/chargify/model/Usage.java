package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@JsonIgnoreProperties( ignoreUnknown = true )
@JsonInclude( JsonInclude.Include.NON_NULL )
@NoArgsConstructor
@Data
public class Usage
{
  @JsonProperty( "created_at" )
  private Date createdAt;

  private String id;

  private String memo;

  private Integer quantity;

  @JsonProperty( "price_point_id" )
  private Integer pricePointId;
}
