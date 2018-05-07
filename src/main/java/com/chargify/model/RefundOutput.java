package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties( ignoreUnknown = true )
@JsonInclude( JsonInclude.Include.NON_NULL )
public class RefundOutput
{
  private String id;

  private Boolean success;

  @JsonProperty( "amount_in_cents" )
  private int amountInCents;

  private String memo;

  public RefundOutput()
  {
  }

  public String getId()
  {
    return id;
  }

  public void setId( String id )
  {
    this.id = id;
  }

  public Boolean getSuccess()
  {
    return success;
  }

  public void setSuccess( Boolean success )
  {
    this.success = success;
  }

  public int getAmountInCents()
  {
    return amountInCents;
  }

  public void setAmountInCents( int amountInCents )
  {
    this.amountInCents = amountInCents;
  }

  public String getMemo()
  {
    return memo;
  }

  public void setMemo( String memo )
  {
    this.memo = memo;
  }

  @Override
  public String toString()
  {
    return "RefundOutput{" +
            "id='" + id + '\'' +
            ", success=" + success +
            ", amountInCents=" + amountInCents +
            ", memo='" + memo + '\'' + '}';
  }
}
