package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonInclude( JsonInclude.Include.NON_NULL )
@NoArgsConstructor
@Data
public class PublicSignupPage implements Serializable
{
  private String id;

  @JsonProperty( "return_url" )
  private String returnUrl;

  @JsonProperty( "return_params" )
  private String returnParams;

  private String url;
}
