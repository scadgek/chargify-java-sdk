package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
@JsonIgnoreProperties( ignoreUnknown = true )
@JsonInclude( JsonInclude.Include.NON_NULL )
public class SubscriptionChargePayload implements Serializable
{
  private String amount;
  @JsonProperty( "amount_in_cents" )
  private String amountInCents;
  private String memo;
  @JsonProperty( "period_range_start" )
  private String periodRangeStart;
  @JsonProperty( "period_range_end" )
  private String periodRangeEnd;
  @JsonProperty( "delay_capture" )
  private String delayCapture;
  @JsonProperty( "accrue_on_failure" )
  private String accrueOnFailure = "true";

  public static SubscriptionChargePayload from( SubscriptionCharge subscriptionCharge )
  {
    SubscriptionChargePayload payload = new SubscriptionChargePayload();
    if( subscriptionCharge.getAmount() != null )
      payload.setAmount( String.valueOf( subscriptionCharge.getAmount() ) );
    if( subscriptionCharge.getAmountInCents() != null )
      payload.setAmountInCents( String.valueOf( subscriptionCharge.getAmountInCents() ) );
    payload.setMemo( String.valueOf( subscriptionCharge.getMemo() ) );
    payload.setPeriodRangeStart( subscriptionCharge.getPeriodRangeStart() );
    payload.setPeriodRangeEnd( subscriptionCharge.getPeriodRangeEnd() );
    if( subscriptionCharge.getDelayCapture() != null )
      payload.setDelayCapture( String.valueOf( subscriptionCharge.getDelayCapture() ) );
    if( subscriptionCharge.getAccrueOnFailure() != null )
      payload.setAccrueOnFailure( String.valueOf( subscriptionCharge.getAccrueOnFailure() ) );

    return payload;
  }
}
