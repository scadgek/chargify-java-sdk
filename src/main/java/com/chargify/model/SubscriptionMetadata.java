package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Arrays;

@JsonIgnoreProperties( ignoreUnknown = true )
@JsonInclude( JsonInclude.Include.NON_NULL )
@NoArgsConstructor
@Getter @Setter
public class SubscriptionMetadata implements Serializable
{
  @JsonProperty( "total_count" )
  private int totalCount;

  @JsonProperty( "current_page" )
  private int currentPage;

  @JsonProperty( "total_pages" )
  private int totalPages;

  @JsonProperty( "per_page" )
  private int perPage;

  private Metadata[] metadata;

  @Override
  public String toString()
  {
    return "SubscriptionMetadata{" +
            "totalCount=" + totalCount +
            ", currentPage=" + currentPage +
            ", totalPages=" + totalPages +
            ", perPage=" + perPage +
            ", metadata=" + Arrays.toString( metadata ) +
            '}';
  }
}
