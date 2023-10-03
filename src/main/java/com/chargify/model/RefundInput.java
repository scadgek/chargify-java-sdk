package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties( ignoreUnknown = true )
@JsonInclude( JsonInclude.Include.NON_NULL )
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RefundInput
{
  @JsonProperty( "payment_id" )
  private String paymentId;

  @JsonProperty( "amount_in_cents" )
  private int amountInCents;

  @JsonProperty( "memo" )
  private String memo;
}
