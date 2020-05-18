package com.chargify;

import com.chargify.exceptions.ChargifyResponseErrorHandler;
import com.chargify.exceptions.ResourceNotFoundException;
import com.chargify.model.Allocation;
import com.chargify.model.Component;
import com.chargify.model.ComponentPricePointUpdate;
import com.chargify.model.CreateSubscription;
import com.chargify.model.Customer;
import com.chargify.model.Metadata;
import com.chargify.model.Migration;
import com.chargify.model.Product;
import com.chargify.model.ProductFamily;
import com.chargify.model.ReferralCode;
import com.chargify.model.RenewalPreview;
import com.chargify.model.Subscription;
import com.chargify.model.SubscriptionComponent;
import com.chargify.model.SubscriptionMetadata;
import com.chargify.model.Usage;
import com.chargify.model.wrappers.AllocationWrapper;
import com.chargify.model.wrappers.AnyComponentWrapper;
import com.chargify.model.wrappers.ComponentPricePointUpdatesWrapper;
import com.chargify.model.wrappers.ComponentWrapper;
import com.chargify.model.wrappers.CreateSubscriptionWrapper;
import com.chargify.model.wrappers.CustomerWrapper;
import com.chargify.model.wrappers.MetadataWrapper;
import com.chargify.model.wrappers.MeteredComponentWrapper;
import com.chargify.model.wrappers.MigrationWrapper;
import com.chargify.model.wrappers.OnOffComponentWrapper;
import com.chargify.model.wrappers.ProductFamilyWrapper;
import com.chargify.model.wrappers.ProductWrapper;
import com.chargify.model.wrappers.QuantityBasedComponentWrapper;
import com.chargify.model.wrappers.ReferralCodeWrapper;
import com.chargify.model.wrappers.RenewalPreviewWrapper;
import com.chargify.model.wrappers.SubscriptionComponentWrapper;
import com.chargify.model.wrappers.SubscriptionWrapper;
import com.chargify.model.wrappers.UsageWrapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class ChargifyService implements Chargify
{
  private final RestTemplate httpClient;

  public ChargifyService( final String domain, final String apiKey )
  {
    this.httpClient = new RestTemplateBuilder()
            .uriTemplateHandler( new RootUriTemplateHandler( "https://" + domain + ".chargify.com" ) )
            .basicAuthorization( apiKey, "x" )
            .errorHandler( new ChargifyResponseErrorHandler() )
            .build();

    this.httpClient.getMessageConverters().stream()
            .filter( AbstractJackson2HttpMessageConverter.class::isInstance )
            .map( AbstractJackson2HttpMessageConverter.class::cast )
            .map( AbstractJackson2HttpMessageConverter::getObjectMapper )
            .forEach( mapper -> mapper.disable( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS ) );
  }

  public ChargifyService( final String domain, final String apiKey, int connectTimeoutInMillis,
                          int readTimeoutInMillis )
  {
    this.httpClient = new RestTemplateBuilder()
            .uriTemplateHandler( new RootUriTemplateHandler( "https://" + domain + ".chargify.com" ) )
            .basicAuthorization( apiKey, "x" )
            .setConnectTimeout( connectTimeoutInMillis )
            .setReadTimeout( readTimeoutInMillis )
            .errorHandler( new ChargifyResponseErrorHandler() )
            .build();

    this.httpClient.getMessageConverters().stream()
            .filter( AbstractJackson2HttpMessageConverter.class::isInstance )
            .map( AbstractJackson2HttpMessageConverter.class::cast )
            .map( AbstractJackson2HttpMessageConverter::getObjectMapper )
            .forEach( mapper -> mapper.disable( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS ) );
  }

  @Override
  public ProductFamily createProductFamily( ProductFamily productFamily )
  {
    return httpClient.postForObject( "/product_families.json",
                                     new ProductFamilyWrapper( productFamily ), ProductFamilyWrapper.class )
            .getProductFamily();
  }

  @Override
  public ProductFamily findProductFamilyById( String id )
  {
    try
    {
      return httpClient.getForObject( "/product_families/" + id + ".json",
                                      ProductFamilyWrapper.class )
              .getProductFamily();
    }
    catch( ResourceNotFoundException e )
    {
      return null;
    }
  }

  @Override
  public List<ProductFamily> findAllProductFamilies()
  {
    return Arrays.stream( httpClient.getForObject( "/product_families.json", ProductFamilyWrapper[].class ) )
            .map( ProductFamilyWrapper::getProductFamily )
            .collect( Collectors.toList() );
  }

  @Override
  public ProductFamily archiveProductFamilyById( String id )
  {
    try
    {
      return httpClient.exchange( "/product_families/" + id + ".json", HttpMethod.DELETE,
                                  HttpEntity.EMPTY, ProductFamilyWrapper.class )
                                  .getBody()
              .getProductFamily();
    }
    catch( ResourceNotFoundException e )
    {
      return null;
    }
  }

  @Override
  public Product createProduct( String productFamilyId, Product product )
  {
    return httpClient.postForObject( "/product_families/" + productFamilyId + "/products.json",
                                     new ProductWrapper( product ), ProductWrapper.class )
            .getProduct();
  }

  @Override
  public Product findProductById( String id )
  {
    try
    {
      return httpClient.getForObject( "/products/" + id + ".json", ProductWrapper.class )
              .getProduct();
    }
    catch( ResourceNotFoundException e )
    {
      return null;
    }
  }

  @Override
  public Product findProductByApiHandle( String apiHandle )
  {
    try
    {
      return httpClient.getForObject( "/products/handle/" + apiHandle + ".json",
                                      ProductWrapper.class )
              .getProduct();
    }
    catch( ResourceNotFoundException e )
    {
      return null;
    }
  }

  @Override
  public List<Product> findAllProducts()
  {
    return Arrays.stream( httpClient.getForObject( "/products.json", ProductWrapper[].class ) )
            .map( ProductWrapper::getProduct )
            .collect( Collectors.toList() );
  }

  @Override
  public List<Product> findProductsByProductFamilyId( String productFamilyId )
  {
    return Arrays.stream( httpClient.getForObject( "/product_families/" + productFamilyId + "/products.json",
                                                   ProductWrapper[].class ) )
            .map( ProductWrapper::getProduct )
            .collect( Collectors.toList() );
  }

  @Override
  public Product archiveProductById( String id )
  {
    try
    {
      return httpClient.exchange( "/products/" + id + ".json", HttpMethod.DELETE,
                                  HttpEntity.EMPTY, ProductWrapper.class )
                                  .getBody()
              .getProduct();
    }
    catch( ResourceNotFoundException e )
    {
      return null;
    }
  }

  @Override
  public Subscription createSubscription( CreateSubscription subscription )
  {
    return httpClient.postForObject( "/subscriptions.json", new CreateSubscriptionWrapper( subscription ), SubscriptionWrapper.class )
            .getSubscription();
  }

  @Override
  public Subscription findSubscriptionById( String id )
  {
    try
    {
      return httpClient.getForObject( "/subscriptions/" + id + ".json", SubscriptionWrapper.class )
              .getSubscription();
    }
    catch( ResourceNotFoundException e )
    {
      return null;
    }
  }

  @Override
  public List<Subscription> findSubscriptionsByCustomerId( String customerId )
  {
    return Arrays.stream( httpClient.getForObject( "/customers/" + customerId + "/subscriptions.json",
                                                   SubscriptionWrapper[].class ) )
            .map( SubscriptionWrapper::getSubscription )
            .collect( Collectors.toList() );
  }

  @Override
  public List<Subscription> findAllSubscriptions()
  {
    return Arrays.stream( httpClient.getForObject( "/subscriptions.json", SubscriptionWrapper[].class ) )
            .map( SubscriptionWrapper::getSubscription )
            .collect( Collectors.toList() );
  }

  @Override
  public List<Subscription> findSubscriptionsByState( String state, int pageNumber, int pageSize )
  {
    return Arrays.stream( httpClient.getForObject( "/subscriptions.json?page=" + pageNumber + "&" +
                                                           "per_page=" + pageSize + "&state=" + state,
                                                   SubscriptionWrapper[].class ) )
            .map( SubscriptionWrapper::getSubscription )
            .collect( Collectors.toList() );
  }

  @Override
  public Subscription cancelSubscriptionById( String id )
  {
    try
    {
      return httpClient.exchange( "/subscriptions/" + id + ".json", HttpMethod.DELETE,
                                  HttpEntity.EMPTY, SubscriptionWrapper.class )
                                  .getBody()
              .getSubscription();
    }
    catch( ResourceNotFoundException e )
    {
      return null;
    }
  }

  @Override
  public Subscription cancelSubscriptionProductChange( String subscriptionId )
  {
    final Subscription subscription = new Subscription();
    subscription.setNextProductId( "" );

    return httpClient.exchange( "/subscriptions/" + subscriptionId + ".json", HttpMethod.PUT,
                                new HttpEntity<>( new SubscriptionWrapper( subscription ) ), SubscriptionWrapper.class )
            .getBody()
            .getSubscription();
  }

  @Override
  public Subscription migrateSubscription( String subscriptionId, String productHandle )
  {
    final Migration migration = new Migration();
    migration.setProductHandle( productHandle );

    return httpClient.postForObject( "/subscriptions/" + subscriptionId + "/migrations.json",
                                     new MigrationWrapper( migration ), SubscriptionWrapper.class )
            .getSubscription();
  }

  @Override
  public Subscription reactivateSubscription( String subscriptionId )
  {
    return httpClient.exchange( "/subscriptions/" + subscriptionId + "/reactivate.json", HttpMethod.PUT,
                                HttpEntity.EMPTY, SubscriptionWrapper.class )
            .getBody()
            .getSubscription();
  }

  @Override
  public ComponentPricePointUpdate migrateSubscriptionComponentToPricePoint( String subscriptionId, int componentId,
                                                                             String pricePointHandle )
  {
    return httpClient.postForObject( "/subscriptions/" + subscriptionId + "/price_points.json",
                                     new ComponentPricePointUpdatesWrapper(
                                             new ComponentPricePointUpdate( componentId, pricePointHandle ) ),
                                     ComponentPricePointUpdatesWrapper.class )
            .getPricePointUpdates()[ 0 ];
  }

  @Override
  public Subscription changeSubscriptionProduct( String subscriptionId, String productHandle, boolean delayed )
  {
    final Subscription subscription = new Subscription();
    subscription.setProductHandle( productHandle );
    subscription.setProductChangeDelayed( delayed );

    return httpClient.exchange( "/subscriptions/" + subscriptionId + ".json", HttpMethod.PUT,
                                new HttpEntity<>( new SubscriptionWrapper( subscription ) ), SubscriptionWrapper.class )
            .getBody()
            .getSubscription();
  }

  @Override
  public RenewalPreview previewSubscriptionRenewal( String subscriptionId )
  {
    return httpClient.postForObject( "/subscriptions/" + subscriptionId + "/renewals/preview.json",
                                     HttpEntity.EMPTY, RenewalPreviewWrapper.class )
            .getRenewalPreview();
  }

  @Override
  public List<Metadata> createSubscriptionMetadata( String subscriptionId, Metadata... metadata )
  {
    return Arrays.asList( httpClient.postForObject( "/subscriptions/" + subscriptionId + "/metadata.json",
                                                    new MetadataWrapper( metadata ), Metadata[].class ) );
  }

  @Override
  public SubscriptionMetadata readSubscriptionMetadata( String subscriptionId )
  {
    try
    {
      return httpClient.getForObject( "/subscriptions/" + subscriptionId + "/metadata.json",
                                      SubscriptionMetadata.class );
    }
    catch( ResourceNotFoundException e )
    {
      return null;
    }
  }

  @Override
  public List<Metadata> updateSubscriptionMetadata( String subscriptionId, Metadata... metadata )
  {
    return Arrays.asList( httpClient.exchange( "/subscriptions/" + subscriptionId + "/metadata.json",
                                               HttpMethod.PUT,
                                               new HttpEntity<>( new MetadataWrapper( metadata ) ), Metadata[].class )
                                  .getBody() );
  }

  @Override
  public Component createComponent( String productFamilyId, Component component )
  {
    if( component.getKind() == null )
      throw new IllegalArgumentException( "Component Kind must not be null" );

    final String pluralKindPathParam;
    final ComponentWrapper componentWrapper;
    switch( component.getKind() )
    {
      case quantity_based_component:
        pluralKindPathParam = "quantity_based_components";
        componentWrapper = new QuantityBasedComponentWrapper( component );
        break;
      case metered_component:
        pluralKindPathParam = "metered_components";
        componentWrapper = new MeteredComponentWrapper( component );
        break;
      case on_off_component:
        pluralKindPathParam = "on_off_components";
        componentWrapper = new OnOffComponentWrapper( component );
        break;
      default:
        throw new IllegalArgumentException( "Invalid component kind - " + component.getKind() );
    }

    return httpClient.postForObject( "/product_families/" + productFamilyId + "/" + pluralKindPathParam + ".json",
                                     componentWrapper, AnyComponentWrapper.class )
            .getComponent();
  }

  @Override
  public Allocation createComponentAllocation( String subscriptionId, String componentId, Allocation allocation )
  {
    return httpClient.postForObject( "/subscriptions/" + subscriptionId + "/components/" + componentId +
                                             "/allocations.json",
                                     new AllocationWrapper( allocation ), AllocationWrapper.class )
            .getAllocation();
  }

  @Override
  public List<Component> findComponentsByProductFamily( String productFamilyId )
  {
    return Arrays.stream( httpClient.getForObject( "/product_families/" + productFamilyId + "/components.json",
                                                   AnyComponentWrapper[].class ) )
            .map( AnyComponentWrapper::getComponent )
            .collect( Collectors.toList() );
  }

  @Override
  public Component findComponentByIdAndProductFamily( String componentId, String productFamilyId )
  {
    try
    {
      return httpClient.getForObject( "/product_families/" + productFamilyId +
                                                           "/components/" + componentId + ".json",
                                      AnyComponentWrapper.class )
              .getComponent();
    }
    catch( ResourceNotFoundException e )
    {
      return null;
    }
  }

  @Override
  public List<SubscriptionComponent> findSubscriptionComponents( String subscriptionId )
  {
    return Arrays.stream( httpClient.getForObject( "/subscriptions/" + subscriptionId + "/components.json",
                                                   SubscriptionComponentWrapper[].class ) )
            .map( SubscriptionComponentWrapper::getComponent )
            .collect( Collectors.toList() );
  }

  @Override
  public SubscriptionComponent findSubscriptionComponentById( String subscriptionId, String componentId )
  {
    try
    {
      return httpClient.getForObject( "/subscriptions/" + subscriptionId +
                                                           "/components/" + componentId + ".json",
                                      SubscriptionComponentWrapper.class )
              .getComponent();
    }
    catch( ResourceNotFoundException e )
    {
      return null;
    }
  }

  @Override
  public Usage reportSubscriptionComponentUsage( String subscriptionId, String componentId, Usage usage )
  {
    return httpClient.postForObject( "/subscriptions/" + subscriptionId + "/components/" + componentId +
                                             "/usages.json",
                                     new UsageWrapper( usage ), UsageWrapper.class )
            .getUsage();
  }

  @Override
  public Customer createCustomer( Customer customer )
  {
    return httpClient.postForObject( "/customers.json", new CustomerWrapper( customer ), CustomerWrapper.class )
            .getCustomer();
  }

  @Override
  public Customer updateCustomer( Customer customer )
  {
    return httpClient.exchange( "/customers/" + customer.getId() + ".json", HttpMethod.PUT,
                                new HttpEntity<>( new CustomerWrapper( customer ) ), CustomerWrapper.class )
            .getBody()
            .getCustomer();
  }

  @Override
  public Customer findCustomerById( String id )
  {
    try
    {
      return httpClient.getForObject( "/customers/" + id + ".json", CustomerWrapper.class )
              .getCustomer();
    }
    catch( ResourceNotFoundException e )
    {
      return null;
    }
  }

  @Override
  public Customer findCustomerByReference( String reference )
  {
    try
    {
      return httpClient.getForObject( "/customers/lookup.json?reference={reference}",
                                      CustomerWrapper.class, reference )
              .getCustomer();
    }
    catch( ResourceNotFoundException e )
    {
      return null;
    }
  }

  @Override
  public List<Customer> findAllCustomers()
  {
    return Arrays.stream( httpClient.getForObject( "/customers.json", CustomerWrapper[].class ) )
            .map( CustomerWrapper::getCustomer )
            .collect( Collectors.toList() );
  }

  @Override
  public void deleteCustomerById( String id )
  {
    try
    {
      httpClient.delete( "/customers/" + id + ".json" );
    }
    catch( ResourceNotFoundException ignored )
    {
    }
  }

  @Override
  public ReferralCode validateReferralCode( String code )
  {
    try
    {
      return httpClient.getForObject( "/referral_codes/validate.json?code=" + code,
                                      ReferralCodeWrapper.class )
              .getReferralCode();
    }
    catch( ResourceNotFoundException e )
    {
      return null;
    }
  }
}
