package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude( JsonInclude.Include.NON_NULL )
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class RenewalPreview
{
  @JsonProperty( "subtotal_in_cents" )
  private Long subtotalInCents;

  @JsonProperty( "total_amount_due_in_cents" )
  private Long totalAmountDueInCents;

  @JsonProperty( "existing_balance_in_cents" )
  private Long existingBalanceInCents;
}
