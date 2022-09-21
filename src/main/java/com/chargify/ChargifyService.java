package com.chargify;

import com.chargify.exceptions.ChargifyResponseErrorHandler;
import com.chargify.exceptions.ResourceNotFoundException;
import com.chargify.model.*;
import com.chargify.model.product.Product;
import com.chargify.model.product.ProductFamily;
import com.chargify.model.product.ProductPricePoint;
import com.chargify.model.wrappers.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public final class ChargifyService implements Chargify
{
  private final RestTemplate httpClient;

  private final String chargifyApiUrl;
  private final HttpClient nativeHttpClient;
  private final String basicAuthHeaderValue;
  private final ObjectMapper objectMapper;

  public ChargifyService( final String domain, final String apiKey )
  {
    this( "https://" + domain + ".chargify.com", apiKey,
          new RestTemplateBuilder()
                  .uriTemplateHandler( new RootUriTemplateHandler( "https://" + domain + ".chargify.com" ) )
                  .basicAuthentication( apiKey, "x" )
                  .errorHandler( new ChargifyResponseErrorHandler() )
                  .build() );
  }

  public ChargifyService( final String domain, final String apiKey, int connectTimeoutInMillis,
                          int readTimeoutInMillis )
  {
    this( "https://" + domain + ".chargify.com", apiKey,
          new RestTemplateBuilder()
                  .uriTemplateHandler( new RootUriTemplateHandler( "https://" + domain + ".chargify.com" ) )
                  .basicAuthentication( apiKey, "x" )
                  .setConnectTimeout( Duration.ofMillis( connectTimeoutInMillis ) )
                  .setReadTimeout( Duration.ofMillis( readTimeoutInMillis ) )
                  .errorHandler( new ChargifyResponseErrorHandler() )
                  .build() );
  }

  private ChargifyService( String chargifyApiUrl, String apiKey, RestTemplate httpClient )
  {
    this.chargifyApiUrl = chargifyApiUrl;
    this.httpClient = httpClient;

    this.httpClient.getMessageConverters().stream()
            .filter( AbstractJackson2HttpMessageConverter.class::isInstance )
            .map( AbstractJackson2HttpMessageConverter.class::cast )
            .map( AbstractJackson2HttpMessageConverter::getObjectMapper )
            .forEach( mapper -> mapper.disable( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS ) );

    this.nativeHttpClient = HttpClient.newBuilder()
            .version( HttpClient.Version.HTTP_2 )
            .followRedirects( HttpClient.Redirect.ALWAYS )
            .build();

    String plainCreds = apiKey + ":x";
    String base64Creds = Base64.getEncoder().encodeToString( plainCreds.getBytes() );

    basicAuthHeaderValue = "Basic " + base64Creds;

    this.objectMapper = new ObjectMapper();
    this.objectMapper.disable( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS );
    this.objectMapper.configure( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    this.objectMapper.registerModules( new JavaTimeModule() );
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
  public Set<ProductPricePoint> findProductPricePointsByProductId( String productId )
  {
    try
    {
      return httpClient.getForObject(
              "/products/" + productId + "/price_points.json", ProductPricePointsWrapper.class )
              .getPricePoints();
    }
    catch( ResourceNotFoundException e )
    {
      return null;
    }
  }

  @Override
  public Set<PricePoint> findComponentPricePoints( int componentId )
  {
    try
    {
      return httpClient.getForObject(
              "/components/" + componentId + "/price_points.json", ComponentPricePointsWrapper.class )
              .getPricePoints();
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
  public void updateSubscription( String subscriptionId, UpdateSubscription subscription )
  {
    httpClient.put(
            "/subscriptions/" + subscriptionId + ".json",
            new UpdateSubscriptionWrapper( subscription )
    );
  }

  @Override
  public void updateSubscriptionNextBillingDate( String subscriptionId, LocalDateTime nextBillingDate )
  {
    updateSubscription(
            subscriptionId,
            UpdateSubscription.builder()
                    .nextBillingAt(
                            nextBillingDate.atZone( ZoneId.systemDefault() )
                                    .withZoneSameInstant( ZoneId.of( "UTC" ) )
                                    .toLocalDateTime()
                                    .format( DateTimeFormatter.ofPattern( "yyyy-MM-dd'T'HH:mm:ss'Z'" ) ) ).build()
    );
  }

  @Override
  public SubscriptionChargeResult createSubscriptionCharge( String subscriptionId, SubscriptionCharge subscriptionCharge )
  {
    return httpClient.postForObject( "/subscriptions/" + subscriptionId + "/charges.json",
                                     Map.of( "charge", subscriptionCharge ), SubscriptionChargeWrapper.class )
            .getSubscriptionChargeResult();
  }

  @Override
  public CompletableFuture<Subscription> findSubscriptionById( String id )
  {
    HttpRequest request = HttpRequest.newBuilder()
            .uri( URI.create( chargifyApiUrl + "/subscriptions/" + id + ".json" ) )
            .header( "Authorization", basicAuthHeaderValue )
            .GET()
            .build();

    return nativeHttpClient.sendAsync( request, HttpResponse.BodyHandlers.ofString() )
            .thenApply( response -> handleResponse( response, SubscriptionWrapper.class ) )
            .thenApply( SubscriptionWrapper::getSubscription )
            .exceptionally( e -> {
              Throwable rootCause = ExceptionUtils.getRootCause( e );
              if( rootCause instanceof ResourceNotFoundException )
                return null;
              else if( rootCause instanceof RuntimeException )
                throw (RuntimeException) rootCause;
              else
                throw new RuntimeException( rootCause );
            } );
  }

  @Override
  public List<PaymentProfile> findPaymentProfilesForCustomer( String customerId )
  {
    try
    {
      return Arrays.stream( httpClient.getForObject( "/payment_profiles.json?customer_id=" + customerId, PaymentProfileWrapper[].class ) )
              .map( PaymentProfileWrapper::getPaymentProfile )
              .collect( Collectors.toList() );
    }
    catch( ResourceNotFoundException e )
    {
      return List.of();
    }
  }

  @Override
  public PaymentProfile createPaymentProfile( CreatePaymentProfile paymentProfile )
  {
    Map<String, Object> body = new HashMap<>();
    body.put( "payment_profile", paymentProfile );
    return httpClient.postForObject(
            "/payment_profiles.json", body, PaymentProfileWrapper.class ).getPaymentProfile();
  }

  @Override
  public void updatePaymentProfile( String paymentProfileId, UpdatePaymentProfile paymentProfile )
  {
    Map<String, Object> body = new HashMap<>();
    body.put( "payment_profile", paymentProfile );

    httpClient.put( "/payment_profiles/" + paymentProfileId + ".json", body );
  }


  @Override
  public PaymentProfile updateSubscriptionPaymentProfile( String subscriptionId, String paymentProfileId )
  {
    return httpClient.postForObject(
            "/subscriptions/" + subscriptionId + "/payment_profiles/" + paymentProfileId + "/change_payment_profile.json",
            Map.of(), PaymentProfileWrapper.class ).getPaymentProfile();
  }

  @Override
  public PaymentProfile findPaymentProfileById( String paymentProfileId )
  {
    try
    {
      return httpClient.getForObject( "/payment_profiles/" + paymentProfileId + ".json", PaymentProfileWrapper.class )
              .getPaymentProfile();
    }
    catch( ResourceNotFoundException e )
    {
      return null;
    }
  }

  @Override
  public void deleteUnusedPaymentProfile( String paymentProfileId )
  {
    httpClient.delete( "/payment_profiles/" + paymentProfileId + ".json" );
  }

  @Override
  public void deletePaymentProfile( String subscriptionId, String paymentProfileId )
  {
    httpClient.delete( "/subscriptions/" + subscriptionId + "/payment_profiles/" + paymentProfileId + ".json" );
  }

  @Override
  public List<Subscription> findSubscriptionsByCustomerId( String customerId )
  {
    return findSubscriptionsByCustomerId( customerId, 0, 200 );
  }

  @Override
  public List<Subscription> findSubscriptionsByCustomerId( String customerId, int pageNumber, int pageSize )
  {
    return Arrays.stream( httpClient.getForObject(
            "/customers/" + customerId + "/subscriptions.json?page=" + pageNumber + "&" + "per_page=" + pageSize,
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
  public Subscription purgeSubscription( Subscription subscription )
  {
    return httpClient.postForObject( "/subscriptions/" + subscription.getId() + "/purge.json?ack=" + subscription.getCustomer().getId() +
                                  "&cascade[]=customer&cascade[]=payment_profile",
                              HttpEntity.EMPTY, SubscriptionWrapper.class )
        .getSubscription();
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
  public Subscription migrateSubscription( String subscriptionId, String productHandle, String pricePointHandle )
  {
    final Migration migration = new Migration();
    migration.setProductHandle( productHandle );
    migration.setPricePointHandle( pricePointHandle );

    return httpClient.postForObject( "/subscriptions/" + subscriptionId + "/migrations.json",
                                     new MigrationWrapper( migration ), SubscriptionWrapper.class )
            .getSubscription();
  }

  @Override
  public Subscription reactivateSubscription( String subscriptionId, boolean preserveBalance )
  {
    return httpClient.exchange( "/subscriptions/" + subscriptionId + "/reactivate.json", HttpMethod.PUT,
                                new HttpEntity<>( Map.of( "preserve_balance", preserveBalance ) ),
                                SubscriptionWrapper.class )
            .getBody()
            .getSubscription();
  }

  @Override
  public Subscription reactivateSubscription( String subscriptionId, SubscriptionReactivationData reactivationData )
  {
    return httpClient.exchange(
            prepareSubscriptionReactivationURI( subscriptionId, reactivationData ),
            HttpMethod.PUT,
            HttpEntity.EMPTY,
            SubscriptionWrapper.class
    )
            .getBody()
            .getSubscription();
  }

  @Override
  public ComponentPricePointUpdate migrateSubscriptionComponentToPricePoint( String subscriptionId, int componentId,
                                                                             String pricePointHandle )
  {
    return httpClient.postForObject( "/subscriptions/" + subscriptionId + "/price_points.json",
                                     new ComponentPricePointUpdatesWrapper(
                                             List.of( new ComponentPricePointUpdate( componentId, pricePointHandle ) ) ),
                                     ComponentPricePointUpdatesWrapper.class )
            .getPricePointUpdates().get( 0 );
  }

  @Override
  public List<ComponentPricePointUpdate> bulkUpdateSubscriptionComponentPricePoint( String subscriptionId, List<ComponentPricePointUpdate> items )
  {
    return httpClient.postForObject( "/subscriptions/" + subscriptionId + "/price_points.json",
                                     new ComponentPricePointUpdatesWrapper( items ),
                                     ComponentPricePointUpdatesWrapper.class )
            .getPricePointUpdates();
  }

  @Override
  public Subscription changeSubscriptionProduct( String subscriptionId, String productHandle, boolean delayed )
  {
    final SubscriptionProductUpdate productUpdate = new SubscriptionProductUpdate();
    productUpdate.setProductHandle( productHandle );
    productUpdate.setChangeDelayed( delayed );

    return httpClient.exchange( "/subscriptions/" + subscriptionId + ".json", HttpMethod.PUT,
                                new HttpEntity<>( new SubscriptionProductUpdateWrapper( productUpdate ) ), SubscriptionWrapper.class )
            .getBody()
            .getSubscription();
  }

  @Override
  public Subscription cancelScheduledSubscriptionProductChange( String subscriptionId )
  {
    return httpClient.exchange( "/subscriptions/" + subscriptionId + ".json", HttpMethod.PUT,
                                new HttpEntity<>(
                                        Map.of(
                                                "subscription",
                                                Map.of(
                                                        "next_product_id", "",
                                                        "next_product_price_point_id", ""
                                                )
                                        )
                                ), SubscriptionWrapper.class )
            .getBody()
            .getSubscription();
  }

  @Override
  public Subscription changeSubscriptionProduct( String subscriptionId, String productHandle, String pricePointHandle, boolean delayed )
  {
    final SubscriptionProductUpdate productUpdate = new SubscriptionProductUpdate();
    productUpdate.setProductHandle( productHandle );
    productUpdate.setChangeDelayed( delayed );
    productUpdate.setPricePointHandle( pricePointHandle );

    return httpClient.exchange( "/subscriptions/" + subscriptionId + ".json", HttpMethod.PUT,
                                new HttpEntity<>( new SubscriptionProductUpdateWrapper( productUpdate ) ), SubscriptionWrapper.class )
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
  public Allocation createComponentAllocation( String subscriptionId, int componentId, Allocation allocation )
  {
    return httpClient.postForObject( "/subscriptions/" + subscriptionId + "/components/" + componentId +
                                             "/allocations.json",
                                     new AllocationWrapper( allocation ), AllocationWrapper.class )
            .getAllocation();
  }

  @Override
  public AllocationPreview previewComponentAllocation( String subscriptionId, int componentId, int quantity )
  {
    return httpClient.postForObject( "/subscriptions/" + subscriptionId + "/allocations/preview.json",
                                     Map.of( "allocations", List.of( new AllocationPreview.ComponentAllocationDTO( componentId, quantity ) ) ),
                                     AllocationPreviewWrapper.class )
            .getAllocationPreview();
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
  public Component findComponentByIdAndProductFamily( int componentId, String productFamilyId )
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
  public ComponentWithPricePoints findComponentWithPricePointsByIdAndProductFamily( int componentId, String productFamilyId )
  {
    return new ComponentWithPricePoints( findComponentByIdAndProductFamily( componentId, productFamilyId ),
                                         findComponentPricePoints( componentId ) );
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
  public List<SubscriptionStatement> findSubscriptionStatements(
          String subscriptionId, int page, int pageSize, String sort, String direction )
  {
    if( pageSize > 200 )
      throw new IllegalArgumentException( "Page size can't be bigger than 200" );

    StringBuilder uriBuilder = new StringBuilder();
    uriBuilder.append( "page=" ).append( page );
    uriBuilder.append( "&per_page=" ).append( pageSize );
    if( sort != null )
      uriBuilder.append( "&sort=" ).append( sort );
    if( direction != null )
      uriBuilder.append( "&direction=" ).append( direction );


    return Arrays.stream( httpClient.getForObject(
            "/subscriptions/" + subscriptionId + "/statements.json?" + uriBuilder, SubscriptionStatementWrapper[].class ) )
            .map( SubscriptionStatementWrapper::getStatement )
            .collect( Collectors.toList() );
  }

  @Override
  public SubscriptionComponent findSubscriptionComponentById( String subscriptionId, int componentId )
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
  public Usage reportSubscriptionComponentUsage( String subscriptionId, int componentId, Usage usage )
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
  public Subscription findSubscriptionByReference( String reference )
  {
    try
    {
      return httpClient.getForObject( "/subscriptions/lookup.json?reference={reference}",
                                      SubscriptionWrapper.class, reference )
              .getSubscription();
    }
    catch( ResourceNotFoundException e )
    {
      return null;
    }
  }

  @Override
  public List<Customer> findCustomersBy( Object criterion, int pageNumber )
  {
    return Arrays.stream( httpClient.getForObject( "/customers.json?q={criterion}&page={pageNumber}",
                                                   CustomerWrapper[].class, criterion, pageNumber ) )
        .map( CustomerWrapper::getCustomer )
        .collect( Collectors.toList() );
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

  @Override
  public Adjustment adjust( String subscriptionId, Adjustment adjustment )
  {
    return httpClient.exchange( "/subscriptions/" + subscriptionId + "/adjustments.json", HttpMethod.POST,
                                new HttpEntity<>( new AdjustmentWrapper( adjustment ) ), AdjustmentWrapper.class )
            .getBody()
            .getAdjustment();
  }

  private String prepareSubscriptionReactivationURI( String subscriptionId, SubscriptionReactivationData reactivationData )
  {
    StringBuilder urlBuilder = new StringBuilder( "/subscriptions/" ).append( subscriptionId ).append( "/reactivate.json" );

    urlBuilder.append( "?include_trial=" ).append( reactivationData.isIncludeTrial() ? "1" : "0" );
    urlBuilder.append( "&preserve_balance=" ).append( reactivationData.isPreserveBalance() ? "1" : "0" );
    if( reactivationData.getCouponCode() != null )
    {
      urlBuilder.append( "&coupon_code=" ).append( UriUtils.encode( reactivationData.getCouponCode(), StandardCharsets.UTF_8 ) );
    }
    if( reactivationData.getResume() != null )
    {
      urlBuilder.append( "&resume=" ).append( reactivationData.getResume() ? "true" : "false" );
    }
    if( reactivationData.isForgiveBalance() )
    {
      urlBuilder.append( "&resume%5Bforgive_balance%5D=" ).append( reactivationData.isForgiveBalance() ? "true" : "false" );
    }

    return urlBuilder.toString();
  }

  public <T,E> T handleResponse( HttpResponse<E> response, Class<T> type )
  {
    final E body = response.body();

    if( !(body instanceof String) && !(body instanceof byte[]) )
    {
      throw new IllegalArgumentException( "Body should be String or byte[]" );
    }

    final String strBody = body instanceof String ? (String) body : new String( (byte[]) body );
    final int statusCode = response.statusCode();

    ChargifyResponseErrorHandler.handleError( statusCode, strBody );

    if( type == Void.class )
      return null;

    try
    {
      return objectMapper.readValue( strBody, type );
    }
    catch( JsonProcessingException e )
    {
      throw new RuntimeException( e );
    }
  }
}
