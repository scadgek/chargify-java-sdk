package com.chargify.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
@Data
public class SubscriptionCharge implements Serializable
{
  private Double amount;
  private Long amountInCents;
  private String memo;
  private String periodRangeStart;
  private String periodRangeEnd;
  private Boolean delayCapture;
  private Boolean accrueOnFailure = true;

  public SubscriptionCharge( Long amountInCents, String memo )
  {
    this.amountInCents = amountInCents;
    this.memo = memo;
  }
}
