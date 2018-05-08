package com.chargify.api;

import com.chargify.exceptions.ChargifyResponseErrorHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

public final class Chargify
{
  private final ProductFamilies productFamilies;

  private final RestTemplate httpClient;

  public Chargify( String domain, String apiKey )
  {
    this.httpClient = new RestTemplateBuilder()
            .uriTemplateHandler( new DefaultUriBuilderFactory( "https://" + domain + ".chargify.com" ) )
            .basicAuthorization( apiKey, "x" )
            .errorHandler( new ChargifyResponseErrorHandler() )
            .build();

    this.productFamilies = new ProductFamilies( this );
  }

  public ProductFamilies productFamily()
  {
    return productFamilies;
  }

  RestTemplate httpClient()
  {
    return httpClient;
  }
}
