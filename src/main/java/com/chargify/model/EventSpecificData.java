package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties( ignoreUnknown = true )
@JsonInclude( JsonInclude.Include.NON_NULL )
public class EventSpecificData
{
  @JsonProperty( "product_id" )
  private Integer productId;
  @JsonProperty( "account_transaction_id" )
  private Integer accountTransactionId;
}
