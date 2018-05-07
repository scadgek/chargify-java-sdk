package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude( JsonInclude.Include.NON_NULL )
public class RenewalPreview
{
  @JsonProperty( "subtotal_in_cents" )
  private Integer subtotalInCents;

  @JsonProperty( "total_amount_due_in_cents" )
  private Integer totalAmountDureInCents;

  @JsonProperty( "existing_balance_in_cents" )
  private Integer existingBalanceInCents;

  public Integer getSubtotalInCents()
  {
    return subtotalInCents;
  }

  public void setSubtotalInCents( Integer subtotalInCents )
  {
    this.subtotalInCents = subtotalInCents;
  }

  public Integer getTotalAmountDueInCents()
  {
    return totalAmountDureInCents;
  }

  public void setTotalAmountDureInCents( Integer totalAmountDureInCents )
  {
    this.totalAmountDureInCents = totalAmountDureInCents;
  }

  public Integer getExistingBalanceInCents()
  {
    return existingBalanceInCents;
  }

  public void setExistingBalanceInCents( Integer existingBalanceInCents )
  {
    this.existingBalanceInCents = existingBalanceInCents;
  }
}
