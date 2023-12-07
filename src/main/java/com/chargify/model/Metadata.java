package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonIgnoreProperties( ignoreUnknown = true )
@JsonInclude( JsonInclude.Include.NON_NULL )
@NoArgsConstructor
@Data
public class Metadata implements Serializable
{
  private String value;

  @JsonProperty( "resource_id" )
  private String resourceId;

  private String name;

  @JsonProperty( "current_name" )
  private String currentName;
}
