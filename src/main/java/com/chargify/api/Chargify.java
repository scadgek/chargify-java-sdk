package com.chargify.api;

import com.chargify.exceptions.ChargifyResponseErrorHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.web.client.RestTemplate;

public final class Chargify
{
  private final Components components;
  private final Customers customers;
  private final ProductFamilies productFamilies;
  private final Products products;
  private final ReferralCodes referralCodes;
  private final Subscriptions subscriptions;

  private final RestTemplate httpClient;

  public Chargify( String domain, String apiKey )
  {
    this.httpClient = new RestTemplateBuilder()
            .uriTemplateHandler( new RootUriTemplateHandler( "https://" + domain + ".chargify.com" ) )
            .basicAuthorization( apiKey, "x" )
            .errorHandler( new ChargifyResponseErrorHandler() )
            .build();

    this.productFamilies = new ProductFamilies( this );
    this.products = new Products( this );
    this.subscriptions = new Subscriptions( this );
    this.components = new Components( this );
    this.customers = new Customers( this );
    this.referralCodes = new ReferralCodes( this );
  }

  public Components components()
  {
    return components;
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

  public ReferralCodes referralCodes()
  {
    return referralCodes;
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
