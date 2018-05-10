package com.chargify.api;

import com.chargify.exceptions.ChargifyResponseErrorHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

public final class Chargify
{
  private final ProductFamilies productFamilies;
  private final Products products;
  private final Subscriptions subscriptions;
  private final Customers customers;

  private final RestTemplate httpClient;

  public Chargify( String domain, String apiKey )
  {
    this.httpClient = new RestTemplateBuilder()
            .uriTemplateHandler( new DefaultUriBuilderFactory( "https://" + domain + ".chargify.com" ) )
            .basicAuthorization( apiKey, "x" )
            .errorHandler( new ChargifyResponseErrorHandler() )
            .build();

    this.productFamilies = new ProductFamilies( this );
    this.products = new Products( this );
    this.subscriptions = new Subscriptions( this );
    this.customers = new Customers( this );
  }

  public Customers customers()
  {
    return customers;
  }

  public ProductFamilies productFamilies()
  {
    return productFamilies;
  }

  public Products products()
  {
    return products;
  }

  public Subscriptions subscriptions()
  {
    return subscriptions;
  }

  RestTemplate httpClient()
  {
    return httpClient;
  }
}
