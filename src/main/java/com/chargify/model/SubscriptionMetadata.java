package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Arrays;

@JsonIgnoreProperties( ignoreUnknown = true )
@JsonInclude( JsonInclude.Include.NON_NULL )
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

  public int getTotalCount()
  {
    return totalCount;
  }

  public void setTotalCount( int totalCount )
  {
    this.totalCount = totalCount;
  }

  public int getCurrentPage()
  {
    return currentPage;
  }

  public void setCurrentPage( int currentPage )
  {
    this.currentPage = currentPage;
  }

  public int getTotalPages()
  {
    return totalPages;
  }

  public void setTotalPages( int totalPages )
  {
    this.totalPages = totalPages;
  }

  public int getPerPage()
  {
    return perPage;
  }

  public void setPerPage( int perPage )
  {
    this.perPage = perPage;
  }

  public Metadata[] getMetadata()
  {
    return metadata;
  }

  public void setMetadata( Metadata[] metadata )
  {
    this.metadata = metadata;
  }

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
