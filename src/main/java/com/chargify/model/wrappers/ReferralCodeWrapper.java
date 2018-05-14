package com.chargify.model.wrappers;

import com.chargify.model.ReferralCode;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class ReferralCodeWrapper
{
  private final ReferralCode referralCode;

  @JsonCreator
  public ReferralCodeWrapper( @JsonProperty( "referral_code" ) ReferralCode wrappedReferalCode )
  {
    this.referralCode = wrappedReferalCode;
  }

  public ReferralCode getReferralCode()
  {
    return referralCode;
  }

  @Override
  public String toString()
  {
    return "ReferralCodeWrapper{" +
            "referralCode=" + referralCode +
            '}';
  }
}
