package com.chargify;

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

    chargify = new ChargifyService( domain, apiKey, 5000, 5000 );
  }

  protected static String randomName()
  {
    return UUID.randomUUID().toString();
  }

  protected static String randomEmail()
  {
    return UUID.randomUUID().toString().substring( 24 ) + "@email.com";
  }
}
