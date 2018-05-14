package com.chargify.api;

import com.chargify.exceptions.ResourceNotFoundException;
import com.chargify.model.ComponentPricePointUpdate;
import com.chargify.model.Migration;
import com.chargify.model.RenewalPreview;
import com.chargify.model.Subscription;
import com.chargify.model.wrappers.ComponentPricePointUpdatesWrapper;
import com.chargify.model.wrappers.MigrationWrapper;
import com.chargify.model.wrappers.RenewalPreviewWrapper;
import com.chargify.model.wrappers.SubscriptionWrapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class Subscriptions
{
  private final Chargify chargify;

  Subscriptions( final Chargify chargify )
  {
    this.chargify = chargify;
  }

  public Subscription create( final Subscription subscription )
  {
    return chargify.httpClient()
            .postForObject( "/subscriptions.json", new SubscriptionWrapper( subscription ), SubscriptionWrapper.class )
            .getSubscription();
  }

  public Optional<Subscription> findById( final String id )
  {
    try
    {
      return Optional.of( chargify.httpClient()
                                  .getForObject( "/subscriptions/" + id + ".json", SubscriptionWrapper.class )
                                  .getSubscription() );
    }
    catch( ResourceNotFoundException e )
    {
      return Optional.empty();
    }
  }

  public List<Subscription> findByCustomerId( final String customerId )
  {
    return Arrays.stream( chargify.httpClient()
                                  .getForObject( "/customers/" + customerId + "/subscriptions.json", SubscriptionWrapper[].class ) )
            .map( SubscriptionWrapper::getSubscription )
            .collect( Collectors.toList() );
  }

  public List<Subscription> findAll()
  {
    return Arrays.stream( chargify.httpClient()
                                  .getForObject( "/subscriptions.json", SubscriptionWrapper[].class ) )
            .map( SubscriptionWrapper::getSubscription )
            .collect( Collectors.toList() );
  }

  public List<Subscription> findByState( final String state, final int pageNumber, final int pageSize )
  {
    return Arrays.stream( chargify.httpClient()
                                  .getForObject( "/subscriptions.json?page=" + pageNumber +
                                                         "&per_page=" + pageSize + "&state=" + state,
                                                 SubscriptionWrapper[].class ) )
            .map( SubscriptionWrapper::getSubscription )
            .collect( Collectors.toList() );
  }

  public Optional<Subscription> cancel( final String id )
  {
    try
    {
      return Optional.of( chargify.httpClient()
                                  .exchange( "/subscriptions/" + id + ".json", HttpMethod.DELETE, HttpEntity.EMPTY, SubscriptionWrapper.class )
                                  .getBody()
                                  .getSubscription() );
    }
    catch( ResourceNotFoundException e )
    {
      return Optional.empty();
    }
  }

  public Subscription cancelProductChange( final String subscriptionId )
  {
    final Subscription subscription = new Subscription();
    subscription.setNextProductId( "" );

    return chargify.httpClient()
            .exchange( "/subscriptions/" + subscriptionId + ".json", HttpMethod.PUT,
                       new HttpEntity<>( new SubscriptionWrapper( subscription ) ), SubscriptionWrapper.class )
            .getBody()
            .getSubscription();
  }

  public Subscription migrate( final String subscriptionId, final String productHandle )
  {
    final Migration migration = new Migration();
    migration.setProductHandle( productHandle );

    return chargify.httpClient()
            .postForObject( "/subscriptions/" + subscriptionId + "/migrations.json",
                            new MigrationWrapper( migration ), SubscriptionWrapper.class )
            .getSubscription();
  }

  public ComponentPricePointUpdate migrateComponentToPricePoint( final String subscriptionId,
                                                                 final int componentId,
                                                                 final String pricePointHandle )
  {
    return chargify.httpClient()
            .postForObject( "/subscriptions/" + subscriptionId + "/price_points.json",
                            new ComponentPricePointUpdatesWrapper(
                                    new ComponentPricePointUpdate( componentId, pricePointHandle ) ),
                            ComponentPricePointUpdatesWrapper.class )
            .getPricePointUpdates()[ 0 ];
  }

  public Subscription productChange( final String subscriptionId, final String productHandle, final boolean delayed )
  {
    final Subscription subscription = new Subscription();
    subscription.setProductHandle( productHandle );
    subscription.setProductChangeDelayed( delayed );

    return chargify.httpClient()
            .exchange( "/subscriptions/" + subscriptionId + ".json", HttpMethod.PUT,
                       new HttpEntity<>( new SubscriptionWrapper( subscription ) ), SubscriptionWrapper.class )
            .getBody()
            .getSubscription();
  }

  public RenewalPreview renewalPreview( final String subscriptionId )
  {
    return chargify.httpClient()
            .postForObject( "/subscriptions/" + subscriptionId + "/renewals/preview.json",
                            HttpEntity.EMPTY, RenewalPreviewWrapper.class )
            .getRenewalPreview();
  }
}
