package com.chargify.api;

import com.chargify.exceptions.ResourceNotFoundException;
import com.chargify.model.ReferralCode;
import com.chargify.model.wrappers.ReferralCodeWrapper;

import java.util.Optional;

public final class ReferralCodes
{
  private final Chargify chargify;

  ReferralCodes( final Chargify chargify )
  {
    this.chargify = chargify;
  }

  public Optional<ReferralCode> isValid( final String code )
  {
    try
    {
      return Optional.of( chargify.httpClient()
                                  .getForObject( "/referral_codes/validate.json?code=" + code,
                                                 ReferralCodeWrapper.class )
                                  .getReferralCode() );
    }
    catch( ResourceNotFoundException e )
    {
      return Optional.empty();
    }
  }
}
