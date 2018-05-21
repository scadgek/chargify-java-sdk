package com.chargify.api;

import com.chargify.exceptions.ChargifyResponseErrorHandler;
import com.chargify.model.Component;
import com.chargify.model.ComponentKind;
import com.chargify.model.Price;
import com.chargify.model.PricePoint;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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

  public static void main( String[] args )
  {
    final Chargify chargify = new Chargify( "backendlessv4", "Dl7iD4o2y9lw5YKNVoXhCQNlRz9Yf6rQf5zO0Xe3tU" );
    final Component component = new Component();
    component.setKind( ComponentKind.metered_component );
    component.setName( "Extra API calls v5" );
    component.setUnitName( "API call" );
    component.setPricingScheme( "stairstep" );

    final List<Price> cloud99Pricing = new ArrayList<>();
    int sq = 1;
    int eq = 100_000;
    double p = 1.0;
    while( sq <= 300_000_000 )
    {
      final Price price = new Price();
      price.setStartingQuantity( sq );
      if( eq != 300_000_000 )
      {
        price.setEndingQuantity( eq );
      }
      price.setUnitPrice( p );

      cloud99Pricing.add( price );

      sq += 100_000;
      eq += 100_000;
      p += 1.0;
    }
    component.setPrices( cloud99Pricing.toArray( new Price[ 0 ] ) );

    final List<Price> cloud9Pricing = new ArrayList<>();
    int sq9 = 1;
    int eq9 = 100_000;
    double p9 = 0.8;
    while( sq9 <= 300_000_000 )
    {
      final Price price = new Price();
      price.setStartingQuantity( sq9 );
      if( eq9 != 300_000_000 )
      {
        price.setEndingQuantity( eq9 );
      }
      price.setUnitPrice( p9 );

      cloud9Pricing.add( price );

      sq9 += 100_000;
      eq9 += 100_000;
      p9 += 0.8;
    }
    final PricePoint[] pricePoints = new PricePoint[ 1 ];
    final PricePoint pricePoint = new PricePoint();
    pricePoint.setName( "Cloud 99" );
    pricePoint.setPricingScheme( "stairstep" );
    pricePoint.setPrices( cloud9Pricing.toArray( new Price[ 0 ] ) );
    pricePoints[0] = pricePoint;
    component.setPricePoints( pricePoints );

    chargify.components().create( "986583", component );
  }
}
