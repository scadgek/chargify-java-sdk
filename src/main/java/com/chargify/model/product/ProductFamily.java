package com.chargify.model.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonInclude( JsonInclude.Include.NON_NULL )
@NoArgsConstructor
@Data
public class ProductFamily implements Serializable
{
  private String id;

  private String name;

  private String description;

  private String handle;

  @JsonProperty( "accounting_code" )
  private String accountingCode;

  @JsonCreator
  public ProductFamily( @JsonProperty( "name" ) String name )
  {
    this.name = name;
  }
}
