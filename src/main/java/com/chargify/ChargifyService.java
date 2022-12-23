package com.chargify;

import com.chargify.exceptions.ChargifyResponseErrorHandler;
import com.chargify.exceptions.ResourceNotFoundException;
import com.chargify.model.*;
import com.chargify.model.product.Product;
import com.chargify.model.product.ProductFamily;
import com.chargify.model.product.ProductPricePoint;
import com.chargify.model.wrappers.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.netty.channel.ChannelOption;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ChargifyService implements Chargify
{
  private final WebClient client;

  private final ObjectMapper objectMapper;

  public ChargifyService( final String domain, final String apiKey, int connectTimeoutInMillis,
                          int readTimeoutInMillis )
  {
    String chargifyApiUrl = "https://" + domain + ".chargify.com";
    String plainCreds = apiKey + ":x";
    String base64Creds = Base64.getEncoder().encodeToString( plainCreds.getBytes() );
    String basicAuthHeaderValue = "Basic " + base64Creds;

    this.objectMapper = new ObjectMapper();
    this.objectMapper.disable( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS );
    this.objectMapper.configure( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false );
    this.objectMapper.registerModules( new JavaTimeModule() );

    final int size = 16 * 1024 * 1024;
    final ExchangeStrategies strategies = ExchangeStrategies.builder()
        .codecs( codecs -> codecs.defaultCodecs().maxInMemorySize( size ) )
        .build();

    this.client = WebClient.builder()
        .baseUrl( chargifyApiUrl )
        .exchangeStrategies( strategies )
        .defaultHeader( "Authorization", basicAuthHeaderValue )
        .clientConnector( new ReactorClientHttpConnector(
            reactor.netty.http.client.HttpClient.create()
                .followRedirect( true )
                .option( ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeoutInMillis )
                .responseTimeout( Duration.ofMillis( readTimeoutInMillis ) )
        ) )
        .codecs( clientDefaultCodecsConfigurer -> {
          clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonEncoder( new Jackson2JsonEncoder( objectMapper, MediaType.APPLICATION_JSON ) );
          clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonDecoder( new Jackson2JsonDecoder( objectMapper, MediaType.APPLICATION_JSON ) );
        } )
        .build();
  }

  @Override
  public Mono<ProductFamily> createProductFamily( ProductFamily productFamily )
  {
    return ChargifyResponseErrorHandler.handleError(
        client.post().uri( "/product_families.json" )
            .contentType( MediaType.APPLICATION_JSON )
            .body( Mono.just( new ProductFamilyWrapper( productFamily ) ), ProductFamilyWrapper.class )
            .retrieve() ).bodyToMono( ProductFamilyWrapper.class ).map( ProductFamilyWrapper::getProductFamily );
  }

  @Override
  public Mono<ProductFamily> findProductFamilyById( String id )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.get().uri( "/product_families/" + id + ".json" ).retrieve() )
        .bodyToMono( ProductFamilyWrapper.class ).map( ProductFamilyWrapper::getProductFamily )
        .onErrorResume( ResourceNotFoundException.class, ex -> Mono.empty() );
  }

  @Override
  public Flux<ProductFamily> findAllProductFamilies()
  {
    return ChargifyResponseErrorHandler.handleError(
            client.get().uri( "/product_families.json" ).retrieve() )
        .bodyToFlux( ProductFamilyWrapper.class ).map( ProductFamilyWrapper::getProductFamily );
  }

  @Override
  public Mono<ProductFamily> archiveProductFamilyById( String id )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.delete().uri( "/product_families/" + id + ".json" ).retrieve() )
        .bodyToMono( ProductFamilyWrapper.class ).map( ProductFamilyWrapper::getProductFamily )
        .onErrorResume( ResourceNotFoundException.class, ex -> Mono.empty() );
  }

  @Override
  public Mono<Product> createProduct( String productFamilyId, Product product )
  {
    return ChargifyResponseErrorHandler.handleError(
        client.post().uri( "/product_families/" + productFamilyId + "/products.json" )
            .contentType( MediaType.APPLICATION_JSON )
            .body( Mono.just( new ProductWrapper( product ) ), ProductWrapper.class )
            .retrieve() ).bodyToMono( ProductWrapper.class ).map( ProductWrapper::getProduct );
  }

  @Override
  public Mono<Product> findProductById( String id )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.get().uri( "/products/" + id + ".json" ).retrieve() )
        .bodyToMono( ProductWrapper.class ).map( ProductWrapper::getProduct )
        .onErrorResume( ResourceNotFoundException.class, ex -> Mono.empty() );
  }

  @Override
  public Mono<Product> findProductByApiHandle( String apiHandle )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.get().uri( "/products/handle/" + apiHandle + ".json" ).retrieve() )
        .bodyToMono( ProductWrapper.class ).map( ProductWrapper::getProduct )
        .onErrorResume( ResourceNotFoundException.class, ex -> Mono.empty() );
  }

  @Override
  public Flux<ProductPricePoint> findProductPricePointsByProductId( String productId )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.get().uri( "/products/" + productId + "/price_points.json" ).retrieve() )
        .bodyToMono( ProductPricePointsWrapper.class ).map( ProductPricePointsWrapper::getPricePoints )
        .flatMapMany( Flux::fromIterable )
        .onErrorResume( ResourceNotFoundException.class, ex -> Flux.empty() );
  }

  @Override
  public Flux<PricePoint> findComponentPricePoints( int componentId )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.get().uri( "/components/" + componentId + "/price_points.json" ).retrieve() )
        .bodyToMono( ComponentPricePointsWrapper.class )
        .map( ComponentPricePointsWrapper::getPricePoints )
        .flatMapMany( Flux::fromIterable );
  }

  @Override
  public Flux<Product> findAllProducts()
  {
    return ChargifyResponseErrorHandler.handleError(
            client.get().uri( "/products.json" ).retrieve() )
        .bodyToFlux( ProductWrapper.class ).map( ProductWrapper::getProduct );
  }

  @Override
  public Flux<Product> findProductsByProductFamilyId( String productFamilyId )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.get().uri( "/product_families/" + productFamilyId + "/products.json" ).retrieve() )
        .bodyToFlux( ProductWrapper.class ).map( ProductWrapper::getProduct );
  }

  @Override
  public Mono<Product> archiveProductById( String id )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.delete().uri( "/products/" + id + ".json" ).retrieve() )
        .bodyToMono( ProductWrapper.class ).map( ProductWrapper::getProduct )
        .onErrorResume( ResourceNotFoundException.class, ex -> Mono.empty() );
  }

  @Override
  public Mono<Subscription> createSubscription( CreateSubscription subscription )
  {
    return ChargifyResponseErrorHandler.handleError(
        client.post().uri( "/subscriptions.json" )
            .contentType( MediaType.APPLICATION_JSON )
            .body( Mono.just( new CreateSubscriptionWrapper( subscription ) ), SubscriptionWrapper.class )
            .retrieve() ).bodyToMono( SubscriptionWrapper.class ).map( SubscriptionWrapper::getSubscription );
  }

  @Override
  public Mono<Void> updateSubscription( String subscriptionId, UpdateSubscription subscription )
  {
    return ChargifyResponseErrorHandler.handleError(
        client.put().uri( "/subscriptions/" + subscriptionId + ".json" )
            .contentType( MediaType.APPLICATION_JSON )
            .body( Mono.just( new UpdateSubscriptionWrapper( subscription ) ), UpdateSubscriptionWrapper.class )
            .retrieve() ).bodyToMono( Map.class ).then();
  }

  @Override
  public Mono<Void> updateSubscriptionNextBillingDate( String subscriptionId, LocalDateTime nextBillingDate )
  {
    return updateSubscription(
        subscriptionId,
        UpdateSubscription.builder().nextBillingAt( ChargifyUtil.toChargifyDateString( nextBillingDate ) ).build()
    );
  }

  @Override
  public Mono<SubscriptionChargeResult> createSubscriptionCharge( String subscriptionId, SubscriptionCharge subscriptionCharge )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.post().uri( "/subscriptions/" + subscriptionId + "/charges.json" )
                .contentType( MediaType.APPLICATION_JSON )
                .body( Mono.just( Map.of( "charge", SubscriptionChargePayload.from( subscriptionCharge ) ) ), Map.class )
                .retrieve() )
        .bodyToMono( SubscriptionChargeWrapper.class )
        .map( SubscriptionChargeWrapper::getSubscriptionChargeResult );
  }

  @Override
  public Mono<Subscription> findSubscriptionById( String id )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.get().uri( "/subscriptions/" + id + ".json" ).retrieve() )
        .bodyToMono( SubscriptionWrapper.class )
        .map( SubscriptionWrapper::getSubscription )
        .onErrorResume( ResourceNotFoundException.class, ex -> Mono.empty() );
  }

  @Override
  public Flux<PaymentProfile> findPaymentProfilesForCustomer( String customerId )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.get().uri( "/payment_profiles.json?customer_id=" + customerId ).retrieve() )
        .bodyToFlux( PaymentProfileWrapper.class ).map( PaymentProfileWrapper::getPaymentProfile )
        .onErrorResume( ResourceNotFoundException.class, ex -> Flux.empty() );
  }

  @Override
  public Mono<PaymentProfile> createPaymentProfile( CreatePaymentProfile paymentProfile )
  {
    Map<String, Object> body = new HashMap<>();
    body.put( "payment_profile", paymentProfile );

    return ChargifyResponseErrorHandler.handleError(
            client.post().uri( "/payment_profiles.json" )
                .contentType( MediaType.APPLICATION_JSON )
                .body( Mono.just( body ), Map.class )
                .retrieve() )
        .bodyToMono( PaymentProfileWrapper.class )
        .map( PaymentProfileWrapper::getPaymentProfile );
  }

  @Override
  public Mono<Void> updatePaymentProfile( String paymentProfileId, UpdatePaymentProfile paymentProfile )
  {
    Map<String, Object> body = new HashMap<>();
    body.put( "payment_profile", paymentProfile );

    return ChargifyResponseErrorHandler.handleError(
            client.put().uri( "/payment_profiles/" + paymentProfileId + ".json" )
                .contentType( MediaType.APPLICATION_JSON )
                .body( Mono.just( body ), Map.class )
                .retrieve() )
        .bodyToMono( Map.class )
        .then();
  }

  @Override
  public Mono<PaymentProfile> updateSubscriptionPaymentProfile( String subscriptionId, String paymentProfileId )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.post().uri( "/subscriptions/" + subscriptionId + "/payment_profiles/" + paymentProfileId + "/change_payment_profile.json" )
                .contentType( MediaType.APPLICATION_JSON )
                .body( Mono.just( Map.of() ), Map.class )
                .retrieve() )
        .bodyToMono( PaymentProfileWrapper.class )
        .map( PaymentProfileWrapper::getPaymentProfile );
  }

  @Override
  public Mono<PaymentProfile> findPaymentProfileById( String paymentProfileId )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.get().uri( "/payment_profiles/" + paymentProfileId + ".json" ).retrieve() )
        .bodyToMono( PaymentProfileWrapper.class ).map( PaymentProfileWrapper::getPaymentProfile )
        .onErrorResume( ResourceNotFoundException.class, ex -> Mono.empty() );
  }

  @Override
  public Mono<Void> deleteUnusedPaymentProfile( String paymentProfileId )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.delete().uri( "/payment_profiles/" + paymentProfileId + ".json" ).retrieve() )
        .bodyToMono( Map.class ).then();
  }

  @Override
  public Mono<Void> deletePaymentProfile( String subscriptionId, String paymentProfileId )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.delete().uri( "/subscriptions/" + subscriptionId + "/payment_profiles/" + paymentProfileId + ".json" ).retrieve() )
        .bodyToMono( Map.class ).then();
  }

  @Override
  public Flux<Subscription> findSubscriptionsByCustomerId( String customerId )
  {
    return findSubscriptionsByCustomerId( customerId, 0, 200 );
  }

  @Override
  public Flux<Subscription> findSubscriptionsByCustomerId( String customerId, int pageNumber, int pageSize )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.get().uri( "/customers/" + customerId + "/subscriptions.json?page=" + pageNumber + "&" + "per_page=" + pageSize ).retrieve() )
        .bodyToFlux( SubscriptionWrapper.class ).map( SubscriptionWrapper::getSubscription );
  }

  @Override
  public Flux<Subscription> findAllSubscriptions()
  {
    return ChargifyResponseErrorHandler.handleError(
            client.get().uri( "/subscriptions.json" ).retrieve() )
        .bodyToFlux( SubscriptionWrapper.class ).map( SubscriptionWrapper::getSubscription );
  }

  @Override
  public Mono<Subscription> purgeSubscription( Subscription subscription )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.post().uri( "/subscriptions/" + subscription.getId() + "/purge.json?ack=" + subscription.getCustomer().getId() +
                                   "&cascade[]=customer&cascade[]=payment_profile" )
                .contentType( MediaType.APPLICATION_JSON )
                .body( Mono.just( Map.of() ), Map.class )
                .retrieve() )
        .bodyToMono( SubscriptionWrapper.class )
        .map( SubscriptionWrapper::getSubscription );
  }

  @Override
  public Flux<Subscription> findSubscriptionsByState( String state, int pageNumber, int pageSize )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.get().uri( "/subscriptions.json?page=" + pageNumber + "&" +
                                  "per_page=" + pageSize + "&state=" + state ).retrieve() )
        .bodyToFlux( SubscriptionWrapper.class ).map( SubscriptionWrapper::getSubscription );
  }

  @Override
  public Mono<Subscription> cancelSubscriptionById( String id )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.delete().uri( "/subscriptions/" + id + ".json" ).retrieve() )
        .bodyToMono( SubscriptionWrapper.class )
        .map( SubscriptionWrapper::getSubscription )
        .onErrorResume( ResourceNotFoundException.class, ex -> Mono.empty() );
  }

  @Override
  public Mono<Subscription> cancelSubscriptionProductChange( String subscriptionId )
  {
    final Subscription subscription = new Subscription();
    subscription.setNextProductId( "" );

    return ChargifyResponseErrorHandler.handleError(
            client.put().uri( "/subscriptions/" + subscriptionId + ".json" )
                .contentType( MediaType.APPLICATION_JSON )
                .body( Mono.just( new SubscriptionWrapper( subscription ) ), SubscriptionWrapper.class )
                .retrieve() )
        .bodyToMono( SubscriptionWrapper.class )
        .map( SubscriptionWrapper::getSubscription )
        .onErrorResume( ResourceNotFoundException.class, ex -> Mono.empty() );
  }

  @Override
  public Mono<Subscription> migrateSubscription( String subscriptionId, Migration migration )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.post().uri( "/subscriptions/" + subscriptionId + "/migrations.json" )
                .contentType( MediaType.APPLICATION_JSON )
                .body( Mono.just( new MigrationWrapper( migration ) ), MigrationWrapper.class )
                .retrieve() )
        .bodyToMono( SubscriptionWrapper.class )
        .map( SubscriptionWrapper::getSubscription )
        .onErrorResume( ResourceNotFoundException.class, ex -> Mono.empty() );
  }

  @Override
  public Mono<Subscription> reactivateSubscription( String subscriptionId, boolean preserveBalance )
  {
    return ChargifyResponseErrorHandler.handleError(
        client.put().uri( "/subscriptions/" + subscriptionId + "/reactivate.json" )
            .contentType( MediaType.APPLICATION_JSON )
            .body( Mono.just( Map.of( "preserve_balance", preserveBalance ) ), Map.class )
            .retrieve() ).bodyToMono( SubscriptionWrapper.class ).map( SubscriptionWrapper::getSubscription );
  }

  @Override
  public Mono<Subscription> reactivateSubscription( String subscriptionId,
                                                    SubscriptionReactivationData reactivationData )
  {
    return ChargifyResponseErrorHandler.handleError(
        client.put().uri( prepareSubscriptionReactivationURI( subscriptionId, reactivationData ) )
            .contentType( MediaType.APPLICATION_JSON )
            .body( Mono.just( Map.of() ), Map.class )
            .retrieve() ).bodyToMono( SubscriptionWrapper.class ).map( SubscriptionWrapper::getSubscription );
  }

  @Override
  public Mono<ComponentPricePointUpdate> migrateSubscriptionComponentToPricePoint( String subscriptionId,
                                                                                   int componentId,
                                                                                   String pricePointHandle )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.post().uri( "/subscriptions/" + subscriptionId + "/price_points.json" )
                .contentType( MediaType.APPLICATION_JSON )
                .body( Mono.just( new ComponentPricePointUpdatesWrapper(
                    List.of( new ComponentPricePointUpdate( componentId, pricePointHandle ) ) ) ), ComponentPricePointUpdatesWrapper.class )
                .retrieve() )
        .bodyToMono( ComponentPricePointUpdatesWrapper.class )
        .map( wrapper -> wrapper.getPricePointUpdates().get( 0 ) );
  }

  @Override
  public Flux<ComponentPricePointUpdate> bulkUpdateSubscriptionComponentPricePoint( String subscriptionId,
                                                                                    List<ComponentPricePointUpdate> items )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.post().uri( "/subscriptions/" + subscriptionId + "/price_points.json" )
                .contentType( MediaType.APPLICATION_JSON )
                .body( Mono.just( new ComponentPricePointUpdatesWrapper( items ) ), ComponentPricePointUpdatesWrapper.class )
                .retrieve() )
        .bodyToMono( ComponentPricePointUpdatesWrapper.class )
        .map( ComponentPricePointUpdatesWrapper::getPricePointUpdates )
        .flatMapMany( Flux::fromIterable );
  }

  @Override
  public Mono<Subscription> cancelScheduledSubscriptionProductChange( String subscriptionId )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.put().uri( "/subscriptions/" + subscriptionId + ".json" )
                .contentType( MediaType.APPLICATION_JSON )
                .body( Mono.just( Map.of(
                    "subscription",
                    Map.of(
                        "next_product_id", "",
                        "next_product_price_point_id", ""
                    )
                ) ), Map.class )
                .retrieve() )
        .bodyToMono( SubscriptionWrapper.class )
        .map( SubscriptionWrapper::getSubscription );
  }

  @Override
  public Mono<Subscription> changeSubscriptionProduct( String subscriptionId, SubscriptionProductUpdate payload )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.put().uri( "/subscriptions/" + subscriptionId + ".json" )
                .contentType( MediaType.APPLICATION_JSON )
                .body( Mono.just( new SubscriptionProductUpdateWrapper( payload ) ), SubscriptionProductUpdateWrapper.class )
                .retrieve() )
        .bodyToMono( SubscriptionWrapper.class )
        .map( SubscriptionWrapper::getSubscription );
  }

  @Override
  public Mono<RenewalPreview> previewSubscriptionRenewal( String subscriptionId )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.post().uri( "/subscriptions/" + subscriptionId + "/renewals/preview.json" )
                .contentType( MediaType.APPLICATION_JSON )
                .body( Mono.just( Map.of() ), Map.class )
                .retrieve() )
        .bodyToMono( RenewalPreviewWrapper.class )
        .map( RenewalPreviewWrapper::getRenewalPreview );
  }

  @Override
  public Flux<Metadata> createSubscriptionMetadata( String subscriptionId, Metadata... metadata )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.post().uri( "/subscriptions/" + subscriptionId + "/metadata.json" )
                .contentType( MediaType.APPLICATION_JSON )
                .body( Mono.just( new MetadataWrapper( metadata ) ), MetadataWrapper.class )
                .retrieve() )
        .bodyToFlux( Metadata.class );
  }

  @Override
  public Mono<SubscriptionMetadata> readSubscriptionMetadata( String subscriptionId )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.get().uri( "/subscriptions/" + subscriptionId + "/metadata.json" ).retrieve() )
        .bodyToMono( SubscriptionMetadata.class )
        .onErrorResume( ResourceNotFoundException.class, ex -> Mono.empty() );
  }

  @Override
  public Flux<Metadata> updateSubscriptionMetadata( String subscriptionId, Metadata... metadata )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.put().uri( "/subscriptions/" + subscriptionId + "/metadata.json" )
                .contentType( MediaType.APPLICATION_JSON )
                .body( Mono.just( new MetadataWrapper( metadata ) ), MetadataWrapper.class )
                .retrieve() )
        .bodyToFlux( Metadata.class );
  }

  @Override
  public Mono<Component> createComponent( String productFamilyId, Component component )
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

    return ChargifyResponseErrorHandler.handleError(
            client.post().uri( "/product_families/" + productFamilyId + "/" + pluralKindPathParam + ".json" )
                .contentType( MediaType.APPLICATION_JSON )
                .body( Mono.just( componentWrapper ), Object.class )
                .retrieve() )
        .bodyToMono( AnyComponentWrapper.class )
        .map( AnyComponentWrapper::getComponent );
  }

  @Override
  public Mono<Allocation> createComponentAllocation( String subscriptionId, int componentId, Allocation allocation )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.post().uri( "/subscriptions/" + subscriptionId + "/components/" + componentId +
                                   "/allocations.json" )
                .contentType( MediaType.APPLICATION_JSON )
                .body( Mono.just( new AllocationWrapper( allocation ) ), AllocationWrapper.class )
                .retrieve() ).
        bodyToMono( AllocationWrapper.class )
        .map( AllocationWrapper::getAllocation );
  }

  @Override
  public Mono<AllocationPreview> previewComponentAllocation( String subscriptionId, int componentId, int quantity )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.post().uri( "/subscriptions/" + subscriptionId + "/allocations/preview.json" )
                .contentType( MediaType.APPLICATION_JSON )
                .body( Mono.just( Map.of( "allocations", List.of( new AllocationPreview.ComponentAllocationDTO( componentId, quantity ) ) ) ), Map.class )
                .retrieve() )
        .bodyToMono( AllocationPreviewWrapper.class )
        .map( AllocationPreviewWrapper::getAllocationPreview );
  }

  @Override
  public Flux<Component> findComponentsByProductFamily( String productFamilyId )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.get().uri( "/product_families/" + productFamilyId + "/components.json" ).retrieve() )
        .bodyToFlux( AnyComponentWrapper.class )
        .map( AnyComponentWrapper::getComponent );
  }

  @Override
  public Mono<Component> findComponentByIdAndProductFamily( int componentId, String productFamilyId )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.get().uri( "/product_families/" + productFamilyId + "/components/" + componentId + ".json" ).retrieve() )
        .bodyToMono( AnyComponentWrapper.class )
        .map( AnyComponentWrapper::getComponent );
  }

  @Override
  public Mono<ComponentWithPricePoints> findComponentWithPricePointsByIdAndProductFamily( int componentId,
                                                                                          String productFamilyId )
  {
    return findComponentByIdAndProductFamily( componentId, productFamilyId )
        .flatMap( component -> findComponentPricePoints( componentId )
            .collect( Collectors.toSet() )
            .map( componentPricePoints -> new ComponentWithPricePoints( component, componentPricePoints ) ) );
  }

  @Override
  public Flux<SubscriptionComponent> findSubscriptionComponents( String subscriptionId )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.get().uri( "/subscriptions/" + subscriptionId + "/components.json" ).retrieve() )
        .bodyToFlux( SubscriptionComponentWrapper.class )
        .map( SubscriptionComponentWrapper::getComponent );
  }

  @Override
  public Flux<SubscriptionStatement> findSubscriptionStatements(
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

    return ChargifyResponseErrorHandler.handleError(
            client.get().uri( "/subscriptions/" + subscriptionId + "/statements.json?" + uriBuilder ).retrieve() )
        .bodyToFlux( SubscriptionStatementWrapper.class )
        .map( SubscriptionStatementWrapper::getStatement );
  }

  @Override
  public Flux<Transaction> findSubscriptionTransactions( String subscriptionId, SubscriptionTransactionsSearchOptions options )
  {
    if( options.getPageSize() > 200 )
      throw new IllegalArgumentException( "Page size can't be bigger than 200" );

    StringBuilder uriBuilder = new StringBuilder();
    uriBuilder.append( "page=" ).append( options.getPage() );
    uriBuilder.append( "&per_page=" ).append( options.getPageSize() );
    uriBuilder.append( "&direction=" ).append( options.getDirection().getValue() );
    if( options.getMaxId() != null )
      uriBuilder.append( "&max_id=" ).append( options.getMaxId() );
    if( options.getSinceId() != null )
      uriBuilder.append( "&since_id=" ).append( options.getSinceId() );
    if( options.getKinds() != null )
      options.getKinds().forEach( kind -> uriBuilder.append( "&kinds[]=" ).append( kind ) );

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd");
    if( options.getSinceDate() != null )
      uriBuilder.append( "&since_date=" ).append( options.getSinceDate().format( dateFormatter ) );
    if( options.getUntilDate() != null )
      uriBuilder.append( "&until_date=" ).append( options.getUntilDate().format( dateFormatter ) );


    return ChargifyResponseErrorHandler.handleError(
            client.get().uri( "/subscriptions/" + subscriptionId + "/transactions.json?" + uriBuilder ).retrieve() )
        .bodyToFlux( TransactionWrapper.class )
        .map( TransactionWrapper::getTransaction );
  }

  @Override
  public Mono<SubscriptionComponent> findSubscriptionComponentById( String subscriptionId, int componentId )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.get().uri( "/subscriptions/" + subscriptionId + "/components/" + componentId + ".json" ).retrieve() )
        .bodyToMono( SubscriptionComponentWrapper.class )
        .map( SubscriptionComponentWrapper::getComponent )
        .onErrorResume( ResourceNotFoundException.class, ex -> Mono.empty() );
  }

  @Override
  public Mono<Usage> reportSubscriptionComponentUsage( String subscriptionId, int componentId, Usage usage )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.post().uri( "/subscriptions/" + subscriptionId + "/components/" + componentId + "/usages.json" )
                .body( Mono.just( new UsageWrapper( usage ) ), UsageWrapper.class ).retrieve() )
        .bodyToMono( UsageWrapper.class )
        .map( UsageWrapper::getUsage );
  }

  @Override
  public Mono<Customer> createCustomer( Customer customer )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.post().uri( "/customers.json" )
                .body( Mono.just( new CustomerWrapper( customer ) ), CustomerWrapper.class ).retrieve() )
        .bodyToMono( CustomerWrapper.class )
        .map( CustomerWrapper::getCustomer );
  }

  @Override
  public Mono<Customer> updateCustomer( Customer customer )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.put().uri( "/customers/" + customer.getId() + ".json" )
                .body( Mono.just( new CustomerWrapper( customer ) ), CustomerWrapper.class ).retrieve() )
        .bodyToMono( CustomerWrapper.class )
        .map( CustomerWrapper::getCustomer );
  }

  @Override
  public Mono<Customer> findCustomerById( String id )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.get().uri( "/customers/" + id + ".json" ).retrieve() )
        .bodyToMono( CustomerWrapper.class )
        .map( CustomerWrapper::getCustomer )
        .onErrorResume( ResourceNotFoundException.class, ex -> Mono.empty() );
  }

  @Override
  public Mono<Customer> findCustomerByReference( String reference )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.get().uri( "/customers/lookup.json?reference={reference}", reference ).retrieve() )
        .bodyToMono( CustomerWrapper.class )
        .map( CustomerWrapper::getCustomer )
        .onErrorResume( ResourceNotFoundException.class, ex -> Mono.empty() );
  }

  @Override
  public Mono<Subscription> findSubscriptionByReference( String reference )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.get().uri( "/subscriptions/lookup.json?reference={reference}", reference ).retrieve() )
        .bodyToMono( SubscriptionWrapper.class )
        .map( SubscriptionWrapper::getSubscription )
        .onErrorResume( ResourceNotFoundException.class, ex -> Mono.empty() );
  }

  @Override
  public Flux<Customer> findCustomersBy( Object criterion, int pageNumber )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.get().uri( "/customers.json?q={criterion}&page={pageNumber}", criterion, pageNumber ).retrieve() )
        .bodyToFlux( CustomerWrapper.class )
        .map( CustomerWrapper::getCustomer );
  }

  @Override
  public Flux<Customer> findAllCustomers()
  {
    return ChargifyResponseErrorHandler.handleError(
            client.get().uri( "/customers.json" ).retrieve() )
        .bodyToFlux( CustomerWrapper.class )
        .map( CustomerWrapper::getCustomer );
  }

  @Override
  public Mono<Void> deleteCustomerById( String id )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.delete().uri( "/customers/" + id + ".json" ).retrieve() )
        .bodyToMono( Map.class )
        .then()
        .onErrorResume( ResourceNotFoundException.class, ex -> Mono.just( "stub" ).then() );
  }

  @Override
  public Mono<ReferralCode> validateReferralCode( String code )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.get().uri( "/referral_codes/validate.json?code=" + code ).retrieve() )
        .bodyToMono( ReferralCodeWrapper.class )
        .map( ReferralCodeWrapper::getReferralCode )
        .onErrorResume( ResourceNotFoundException.class, ex -> Mono.empty() );
  }

  @Override
  public Mono<Adjustment> adjust( String subscriptionId, Adjustment adjustment )
  {
    return ChargifyResponseErrorHandler.handleError(
            client.post().uri( "/subscriptions/" + subscriptionId + "/adjustments.json" )
                .body( Mono.just( new AdjustmentWrapper( adjustment ) ), AdjustmentWrapper.class ).retrieve() )
        .bodyToMono( AdjustmentWrapper.class )
        .map( AdjustmentWrapper::getAdjustment );
  }

  private String prepareSubscriptionReactivationURI( String subscriptionId,
                                                     SubscriptionReactivationData reactivationData )
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
}
