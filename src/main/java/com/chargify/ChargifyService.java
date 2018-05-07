package com.chargify;

import com.chargify.exceptions.ChargifyException;
import com.chargify.model.Allocation;
import com.chargify.model.ComponentCreateInput;
import com.chargify.model.ComponentDefinition;
import com.chargify.model.Customer;
import com.chargify.model.Migration;
import com.chargify.model.OnOffComponentCreateInput;
import com.chargify.model.Product;
import com.chargify.model.QuantityBasedComponentCreateInput;
import com.chargify.model.RenewalPreview;
import com.chargify.model.SubscriptionComponent;
import com.chargify.model.SubscriptionInput;
import com.chargify.model.SubscriptionOutput;
import com.chargify.model.ValidationReferralCodeOutput;
import com.chargify.model.wrappers.AllocationWrapper;
import com.chargify.model.wrappers.ComponentDefinitionWrapper;
import com.chargify.model.wrappers.CustomerWrapper;
import com.chargify.model.wrappers.MigrationWrapper;
import com.chargify.model.wrappers.OnOffComponentCreateInputWrapper;
import com.chargify.model.wrappers.ProductWrapper;
import com.chargify.model.wrappers.QuantityBasedComponentCreateInputWrapper;
import com.chargify.model.wrappers.RenewalPreviewWrapper;
import com.chargify.model.wrappers.SubscriptionComponentWrapper;
import com.chargify.model.wrappers.SubscriptionInputWrapper;
import com.chargify.model.wrappers.SubscriptionOutputWrapper;
import com.chargify.model.wrappers.ValidationReferralCodeOutputWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChargifyService implements Chargify
{
  private static final Logger logger = LoggerFactory.getLogger( ChargifyService.class );
  private final RestTemplate restTemplate;
  private final ObjectMapper objectMapper = new ObjectMapper();

  public ChargifyService( String domain, String apiKey )
  {
    this.restTemplate = new RestTemplateBuilder()
            .uriTemplateHandler( new DefaultUriBuilderFactory( "https://" + domain + ".chargify.com" ) )
            .basicAuthorization( apiKey, "x" )
            .build();
  }

  public ChargifyService( String domain, String apiKey, RestTemplate restTemplate )
  {
    this.restTemplate = restTemplate;

    restTemplate.setUriTemplateHandler( new DefaultUriBuilderFactory( "https://" + domain + ".chargify.com" ) );
    restTemplate.getInterceptors().add( new BasicAuthorizationInterceptor( apiKey, "x" ) );
  }

  @Override
  public Customer createCustomer( Customer customer )
  {
    try
    {
      ResponseEntity<CustomerWrapper> response = restTemplate.postForEntity( Endpoints.CREATE_CUSTOMER,
                                                                             new CustomerWrapper( customer ),
                                                                             CustomerWrapper.class );
      return response.getBody().getCustomer();
    }
    catch( Exception e )
    {
      throw newChargifyException( e );
    }
  }

  @Override
  public void deleteCustomer( String customerId )
  {
    try
    {
      restTemplate.delete( "/customers/" + customerId + ".json" );
    }
    catch( Exception e )
    {
      throw newChargifyException( e );
    }
  }

  @Override
  public SubscriptionOutput createMigration( final String subscriptionId, final Migration migration )
  {
    try
    {
      final ResponseEntity<SubscriptionOutputWrapper> response = restTemplate.postForEntity( Endpoints.CREATE_MIGRATION( subscriptionId ), new MigrationWrapper( migration ), SubscriptionOutputWrapper.class );
      return response.getBody().getSubscription();
    }
    catch( Exception e )
    {
      throw newChargifyException( e );
    }
  }

  @Override
  public SubscriptionOutput createSubscription( SubscriptionInput subscription )
  {
    try
    {
      final SubscriptionInputWrapper requestBody = new SubscriptionInputWrapper( subscription );

      if( logger.isDebugEnabled() )
        logger.debug( "Creating subscription via POST to /subscriptions.json with body {}", objectMapper.valueToTree( requestBody ) );

      ResponseEntity<SubscriptionOutputWrapper> response = restTemplate.postForEntity( "/subscriptions.json", requestBody, SubscriptionOutputWrapper.class );
      return response.getBody().getSubscription();
    }
    catch( RestClientResponseException e )
    {
      logger.error( "Failed to create subscription: " + e.getResponseBodyAsString(), e );
      throw new ChargifyException( "Billing server is temporary unavailable, please try again later" );
    }
    catch( Exception e )
    {
      throw newChargifyException( e );
    }
  }

  @Override
  public Optional<SubscriptionOutput> readSubscription( String subscriptionId )
  {
    try
    {
      ResponseEntity<SubscriptionOutputWrapper> response = restTemplate.getForEntity( Endpoints.READ_SUBSCRIPTION_BY_ID( subscriptionId ), SubscriptionOutputWrapper.class );
      return Optional.of( response.getBody().getSubscription() );
    }
    catch( HttpClientErrorException e )
    {
      if( e.getStatusCode() == HttpStatus.NOT_FOUND )
        return Optional.empty();
      else
        throw newChargifyException( e );
    }
    catch( Exception e )
    {
      throw newChargifyException( e );
    }
  }

  @Override
  public List<SubscriptionOutput> listSubscriptionsByCustomer( String customerId )
  {
    try
    {
      final ResponseEntity<SubscriptionOutputWrapper[]> response =
              restTemplate.getForEntity( Endpoints.LIST_SUBSCRIPTIONS_BY_CUSTOMER( customerId ),
                                         SubscriptionOutputWrapper[].class );

      return Stream.of( response.getBody() )
              .map( SubscriptionOutputWrapper::getSubscription )
              .collect( Collectors.toList() );
    }
    catch( Exception e )
    {
      throw newChargifyException( e );
    }
  }

  /**
   * @param state    The current state of the subscription.
   *                 Allowed Values: active, canceled, expired, expired_cards, on_hold, past_due,
   *                 pending_cancellation, pending_renewal, suspended, trial_ended, trialing, unpaid
   * @param page     Result records are organized in pages.
   *                 The page parameter specifies a page number of results to fetch.
   *                 You can start navigating through the pages to consume the results.
   *                 You do this by passing in a page parameter. Retrieve the next page by adding ?page=2 to the query string.
   *                 If there are no results to return, then an empty result set will be returned.
   * @param pageSize This parameter indicates how many records to fetch in each request.
   *                 The maximum allowed values is 200; any per_page value over 200 will be changed to 200
   * @return This method will return an array of subscriptions from a Site. Pay close attention to query string filters
   * and pagination in order to control responses from the server.
   * @see <a href="https://reference.chargify.com/v1/subscriptions/list-subscriptions">chargify doc</a>
   */
  @Override
  public List<SubscriptionOutput> listSubscriptionsByState( String state, int page, int pageSize )
  {
    try
    {
      final ResponseEntity<SubscriptionOutputWrapper[]> response =
              restTemplate.getForEntity( Endpoints.LIST_SUBSCRIPTIONS_BY_STATE( state, page, pageSize ),
                                         SubscriptionOutputWrapper[].class );

      return Stream.of( response.getBody() ).map( SubscriptionOutputWrapper::getSubscription ).collect( Collectors.toList() );
    }
    catch( Exception e )
    {
      throw newChargifyException( e );
    }
  }

  /**
   * @param state The current state of the subscription.
   *              Allowed Values: active, canceled, expired, expired_cards, on_hold, past_due,
   *              pending_cancellation, pending_renewal, suspended, trial_ended, trialing, unpaid
   * @return number of subscriptions with given state
   */
  @Override
  public int countSubscriptionsByState( String state )
  {
    int count = 0;
    final int pageSize = 200;
    int page = 1;

    List<SubscriptionOutput> subscriptions;

    do
    {
      subscriptions = listSubscriptionsByState( state, page++, pageSize );

      if( subscriptions == null )
        break;

      count += subscriptions.size();
    }
    while( subscriptions.size() > 0 );

    return count;
  }

  @Override
  public int moveTrialEndedToFree()
  {
    int count = 0;
    final int pageSize = 200;
    int page = 1;

    final Migration migration = new Migration();
    migration.setProductHandle( "free" );

    List<SubscriptionOutput> subscriptions;

    do
    {
      subscriptions = listSubscriptionsByState( "trial_ended", page++, pageSize );

      if( subscriptions == null )
        break;

      for( SubscriptionOutput subscription : subscriptions )
      {
        logger.debug( "Try to migrate {} subscription", subscription.getId() );
        try
        {
          createMigration( subscription.getId(), migration );
        }
        catch( ChargifyException e )
        {
          logger.error( "Cannot migrate {} subscription", subscription );
          continue;
        }
        count++;
        logger.debug( "Migrated subscription with id {} count {}", subscription.getId(), count );
      }
    }
    while( subscriptions.size() > 0 );

    return count;
  }

  @Override
  public Optional<SubscriptionOutput> cancelSubscription( String subscriptionId )
  {
    try
    {
      final ResponseEntity<SubscriptionOutputWrapper> response = restTemplate.exchange( "/subscriptions/" + subscriptionId + ".json", HttpMethod.DELETE, HttpEntity.EMPTY, SubscriptionOutputWrapper.class );
      return Optional.of( response.getBody().getSubscription() );
    }
    catch( HttpClientErrorException e )
    {
      if( e.getStatusCode() == HttpStatus.NOT_FOUND )
        return Optional.empty();
      else
        throw newChargifyException( e );
    }
    catch( Exception e )
    {
      throw newChargifyException( e );
    }
  }

  @Override
  public ValidationReferralCodeOutput validateReferralCode( String referralCode )
  {
    try
    {
      ResponseEntity<ValidationReferralCodeOutputWrapper> response = restTemplate
              .getForEntity( "/referral_codes/validate.json?code=" + referralCode,
                             ValidationReferralCodeOutputWrapper.class );

      return response.getBody().getWrappedReferalCode();
    }
    catch( Exception e )
    {
      throw newChargifyException( e );
    }
  }

  @Override
  public ComponentDefinition createComponent( String productFamilyId, ComponentCreateInput componentCreateInput )
  {

    return componentCreateInput instanceof OnOffComponentCreateInput
            ? createOnOfComponent( productFamilyId, (OnOffComponentCreateInput) componentCreateInput )
            : createQuantityBasedComponent( productFamilyId, (QuantityBasedComponentCreateInput) componentCreateInput );
  }

  private ComponentDefinition createOnOfComponent( String productFamilyId,
                                                   OnOffComponentCreateInput onOffComponentCreateInput )
  {
    try
    {
      final ResponseEntity<ComponentDefinitionWrapper> response = restTemplate
              .postForEntity( "/product_families/" + productFamilyId + "/on_off_components.json",
                              new OnOffComponentCreateInputWrapper( onOffComponentCreateInput ),
                              ComponentDefinitionWrapper.class );

      return response.getBody().getComponent();
    }
    catch( HttpClientErrorException e )
    {
      throw newChargifyException( e );
    }
  }

  private ComponentDefinition createQuantityBasedComponent( String productFamilyId,
                                                            QuantityBasedComponentCreateInput quantityBasedComponentCreateInput )
  {
    try
    {
      final ResponseEntity<ComponentDefinitionWrapper> response = restTemplate
              .postForEntity( "/product_families/" + productFamilyId + "/quantity_based_components.json",
                              new QuantityBasedComponentCreateInputWrapper( quantityBasedComponentCreateInput ),
                              ComponentDefinitionWrapper.class );

      return response.getBody().getComponent();
    }
    catch( HttpClientErrorException e )
    {
      throw newChargifyException( e );
    }
  }

  @Override
  public Optional<Product> readProductViaApiHandle( final String apiHandle )
  {
    try
    {
      final ResponseEntity<ProductWrapper> response = restTemplate.getForEntity( Endpoints.READ_PRODUCT_VIA_API_HANDLE( apiHandle ), ProductWrapper.class );
      return Optional.of( response.getBody().getProduct() );
    }
    catch( HttpClientErrorException e )
    {
      if( e.getStatusCode() == HttpStatus.NOT_FOUND )
        return Optional.empty();
      else
        throw newChargifyException( e );
    }
    catch( Exception e )
    {
      throw newChargifyException( e );
    }
  }

  @Override
  public RenewalPreview renewalPreview( final String subscriptionId )
  {
    try
    {
      final ResponseEntity<RenewalPreviewWrapper> response = restTemplate.postForEntity( Endpoints.RENEWAL_PREVIEW( subscriptionId ), null, RenewalPreviewWrapper.class );
      return response.getBody().getRenewalPreview();
    }
    catch( HttpClientErrorException e )
    {
      if( e.getStatusCode() == HttpStatus.NOT_FOUND )
      {
        throw new RuntimeException( "Subscription not found: " + subscriptionId );
      }
      else
      {
        throw newChargifyException( e );
      }
    }
    catch( Exception e )
    {
      throw newChargifyException( e );
    }
  }

  @Override
  public Collection<Product> listProductsByProductFamily( String productFamilyId )
  {
    try
    {
      final ResponseEntity<ProductWrapper[]> response = restTemplate.getForEntity( "/product_families/" + productFamilyId + "/products.json", ProductWrapper[].class );
      return Stream.of( response.getBody() ).map( ProductWrapper::getProduct ).collect( Collectors.toSet() );
    }
    catch( Exception e )
    {
      throw newChargifyException( e );
    }
  }

  @Override
  public Allocation createAllocation( final String subscriptionId, final String componentId, final int quantity,
                                      final String memo )
  {
    try
    {
      final Allocation allocation = new Allocation();
      allocation.setQuantity( quantity );
      allocation.setMemo( memo );

      final String url = Endpoints.CREATE_ALLOCATION( subscriptionId, componentId );
      final ResponseEntity<AllocationWrapper> response = restTemplate.postForEntity( url, new AllocationWrapper( allocation ), AllocationWrapper.class );
      return response.getBody().getAllocation();
    }
    catch( Exception e )
    {
      throw newChargifyException( e );
    }
  }

  @Override
  public Collection<SubscriptionComponent> listComponentsBySubscription( final String subscriptionId )
  {
    try
    {
      final String url = Endpoints.LIST_COMPONENTS_BY_SUBSCRIPTION( subscriptionId );
      final ResponseEntity<SubscriptionComponentWrapper[]> response = restTemplate.getForEntity( url, SubscriptionComponentWrapper[].class );
      return Stream.of( response.getBody() )
              .map( SubscriptionComponentWrapper::getComponent )
              .collect( Collectors.toList() );
    }
    catch( Exception e )
    {
      throw newChargifyException( e );
    }
  }

  @Override
  public ComponentDefinition readComponentByProductFamily( final String productFamilyId,
                                                           final String componentId )
  {
    try
    {
      final ResponseEntity<ComponentDefinitionWrapper> response = restTemplate.getForEntity( "/product_families/" + productFamilyId + "/components/" + componentId + ".json", ComponentDefinitionWrapper.class );
      return response.getBody().getComponent();
    }
    catch( HttpClientErrorException e )
    {
      if( e.getStatusCode() == HttpStatus.NOT_FOUND )
        return null;
      else
        throw newChargifyException( e );
    }
    catch( Exception e )
    {
      throw newChargifyException( e );
    }
  }

  @Override
  public SubscriptionComponent readComponentBySubscription( final String subscriptionId,
                                                            final String componentId )
  {
    try
    {
      final ResponseEntity<SubscriptionComponentWrapper> response = restTemplate.getForEntity( "/subscriptions/" + subscriptionId + "/components/" + componentId + ".json", SubscriptionComponentWrapper.class );
      return response.getBody().getComponent();
    }
    catch( HttpClientErrorException e )
    {
      if( e.getStatusCode() == HttpStatus.NOT_FOUND )
        return null;
      else
        throw newChargifyException( e );
    }
    catch( Exception e )
    {
      throw newChargifyException( e );
    }
  }

  @Override
  public Optional<Customer> readCustomerByReference( String reference )
  {
    try
    {
      ResponseEntity<CustomerWrapper> response = restTemplate.getForEntity( Endpoints.READ_CUSTOMER_BY_REFERENCE( reference ), CustomerWrapper.class );
      return Optional.of( response.getBody().getCustomer() );
    }
    catch( HttpClientErrorException e )
    {
      if( e.getStatusCode() == HttpStatus.NOT_FOUND )
        return Optional.empty();
      else
        throw newChargifyException( e );
    }
    catch( Exception e )
    {
      throw newChargifyException( e );
    }
  }

  private ChargifyException newChargifyException( HttpClientErrorException e )
  {
    logger.warn( "Response info:\n" +
                         "  Raw status code: " + e.getRawStatusCode() + "\n" +
                         "  Status code: " + e.getStatusCode() + "\n" +
                         "  Status text: " + e.getStatusText() + "\n" +
                         "  Response headers: " + e.getResponseHeaders() + "\n" +
                         "  Response body: " + e.getResponseBodyAsString() + "\n" +
                         "  Exception message: " + e.getMessage() + "\n" +
                         "  Most specific cause: " + e.getMostSpecificCause() + "\n" +
                         "  Root cause: " + e.getRootCause() );

    Object errors;
    try
    {
      errors = JsonParserFactory.getJsonParser().parseMap( e.getResponseBodyAsString() ).get( "errors" );
    }
    catch( IllegalArgumentException ex )
    {
      logger.warn( "Couldn't parse exception message: " + e.getResponseBodyAsString(), ex );

      return new ChargifyException( "Billing server is temporary unavailable, please try again later" );
    }

    if( errors instanceof Collection )
      //noinspection unchecked
      return new ChargifyException( (Collection<String>) errors );
    else
      return new ChargifyException( errors.toString() );
  }

  private ChargifyException newChargifyException( Exception e )
  {
    if( e instanceof HttpClientErrorException )
      return newChargifyException( (HttpClientErrorException) e );

    logger.error( "Unhandled exception during chargify call", e );
    return new ChargifyException( "Billing server is temporary unavailable, please try again later" );
  }
}
