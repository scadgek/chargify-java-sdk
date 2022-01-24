package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties( ignoreUnknown = true )
@JsonInclude( JsonInclude.Include.NON_NULL )
public class Event
{
  private Integer id;
  private String key;
  private String message;
  @JsonProperty( "subscription_id" )
  private Integer subscriptionId;
  @JsonProperty( "created_at" )
  private LocalDateTime createdAt;
  @JsonProperty( "event_specific_data" )
  private EventSpecificData eventSpecificData;
}
