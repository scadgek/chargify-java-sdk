package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties( ignoreUnknown = true )
@JsonInclude( JsonInclude.Include.NON_NULL )
@NoArgsConstructor
@Data
public class Payment
{
  private String id;

  private Boolean success;

  @JsonProperty( "amount_in_cents" )
  private int amountInCents;

  private String memo;

  public Payment( String id )
  {
    this.id = id;
  }
}
