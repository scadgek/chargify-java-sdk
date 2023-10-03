package com.chargify.model.wrappers;

import com.chargify.model.PaymentProfile;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public final class PaymentProfileWrapper
{
  private final PaymentProfile paymentProfile;

  @JsonCreator
  public PaymentProfileWrapper( @JsonProperty( "payment_profile" ) PaymentProfile paymentProfile )
  {
    this.paymentProfile = paymentProfile;
  }
}
