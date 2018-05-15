package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties( ignoreUnknown = true )
@JsonInclude( JsonInclude.Include.NON_NULL )
public class Usage
{
  @JsonProperty( "created_at" )
  private Date createdAt;

  private String id;

  private String memo;

  private Integer quantity;

  @JsonProperty( "price_point_id" )
  private Integer pricePointId;

  public Date getCreatedAt()
  {
    return createdAt;
  }

  public void setCreatedAt( Date createdAt )
  {
    this.createdAt = createdAt;
  }

  public String getId()
  {
    return id;
  }

  public void setId( String id )
  {
    this.id = id;
  }

  public String getMemo()
  {
    return memo;
  }

  public void setMemo( String memo )
  {
    this.memo = memo;
  }

  public Integer getQuantity()
  {
    return quantity;
  }

  public void setQuantity( Integer quantity )
  {
    this.quantity = quantity;
  }

  public Integer getPricePointId()
  {
    return pricePointId;
  }

  public void setPricePointId( Integer pricePointId )
  {
    this.pricePointId = pricePointId;
  }

  @Override
  public String toString()
  {
    return "Usage{" +
            "createdAt=" + createdAt +
            ", id='" + id + '\'' +
            ", memo='" + memo + '\'' +
            ", quantity=" + quantity +
            ", pricePointId=" + pricePointId +
            '}';
  }
}
