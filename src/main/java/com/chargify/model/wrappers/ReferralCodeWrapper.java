package com.chargify.model.wrappers;

import com.chargify.model.ReferralCode;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public final class ReferralCodeWrapper
{
  private final ReferralCode referralCode;

  @JsonCreator
  public ReferralCodeWrapper( @JsonProperty( "referral_code" ) ReferralCode wrappedReferralCode )
  {
    this.referralCode = wrappedReferralCode;
  }
}
