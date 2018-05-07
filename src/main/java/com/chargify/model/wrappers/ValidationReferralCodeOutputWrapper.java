package com.chargify.model.wrappers;

import com.chargify.model.ValidationReferralCodeOutput;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class ValidationReferralCodeOutputWrapper
{
  private final ValidationReferralCodeOutput wrappedReferalCode;

  @JsonCreator
  public ValidationReferralCodeOutputWrapper(
          @JsonProperty( "referral_code" )
                  ValidationReferralCodeOutput wrappedReferalCode )
  {
    this.wrappedReferalCode = wrappedReferalCode;
  }

  public ValidationReferralCodeOutput getWrappedReferalCode()
  {
    return wrappedReferalCode;
  }
}
