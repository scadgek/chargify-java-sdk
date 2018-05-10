package com.chargify;

import com.chargify.api.Chargify;

import java.util.UUID;

public abstract class ChargifyTest
{
  protected static final Chargify chargify;

  static
  {
    final String domain = System.getProperty( "chargify.domain" );
    final String apiKey = System.getProperty( "chargify.apikey" );
    if( domain == null || domain.isEmpty() || apiKey == null || apiKey.isEmpty() )
    {
      throw new RuntimeException( "Chargify domain and apikey should be specified in JVM options: " +
                                          "-Dchargify.domain=XXX -Dchargify.apikey=YYY" );
    }

    chargify = new Chargify( domain, apiKey );
  }

  protected static String randomName()
  {
    return UUID.randomUUID().toString();
  }
}
