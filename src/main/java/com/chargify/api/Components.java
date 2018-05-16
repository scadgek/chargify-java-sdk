package com.chargify.api;

import com.chargify.exceptions.ResourceNotFoundException;
import com.chargify.model.Allocation;
import com.chargify.model.Component;
import com.chargify.model.SubscriptionComponent;
import com.chargify.model.Usage;
import com.chargify.model.wrappers.AllocationWrapper;
import com.chargify.model.wrappers.AnyComponentWrapper;
import com.chargify.model.wrappers.ComponentWrapper;
import com.chargify.model.wrappers.MeteredComponentWrapper;
import com.chargify.model.wrappers.OnOffComponentWrapper;
import com.chargify.model.wrappers.QuantityBasedComponentWrapper;
import com.chargify.model.wrappers.SubscriptionComponentWrapper;
import com.chargify.model.wrappers.UsageWrapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class Components
{
  private final Chargify chargify;

  Components( final Chargify chargify )
  {
    this.chargify = chargify;
  }

  public Component create( final String productFamilyId, final Component component )
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

    return chargify.httpClient()
            .postForObject( "/product_families/" + productFamilyId + "/" + pluralKindPathParam + ".json",
                            componentWrapper, AnyComponentWrapper.class )
            .getComponent();
  }

  public Allocation createAllocation( final String subscriptionId, final String componentId, final int quantity,
                                      final String memo )
  {
    final Allocation allocation = new Allocation();
    allocation.setQuantity( quantity );
    allocation.setMemo( memo );

    return chargify.httpClient()
            .postForObject( "/subscriptions/" + subscriptionId + "/components/" + componentId + "/allocations.json",
                            new AllocationWrapper( allocation ), AllocationWrapper.class )
            .getAllocation();
  }

  public List<Component> findByProductFamily( final String productFamilyId )
  {
    return Arrays.stream( chargify.httpClient()
                                  .getForObject( "/product_families/" + productFamilyId + "/components.json",
                                                 AnyComponentWrapper[].class ) )
            .map( AnyComponentWrapper::getComponent )
            .collect( Collectors.toList() );
  }

  public Optional<Component> findByIdAndProductFamily( final String componentId, final String productFamilyId )
  {
    try
    {
      return Optional.of( chargify.httpClient()
                                  .getForObject( "/product_families/" + productFamilyId + "/components/" + componentId + ".json",
                                                 AnyComponentWrapper.class )
                                  .getComponent() );
    }
    catch( ResourceNotFoundException e )
    {
      return Optional.empty();
    }
  }

  public List<SubscriptionComponent> findBySubscriptionId( final String subscriptionId )
  {
    return Arrays.stream( chargify.httpClient()
                                  .getForObject( "/subscriptions/" + subscriptionId + "/components.json", SubscriptionComponentWrapper[].class ) )
            .map( SubscriptionComponentWrapper::getComponent )
            .collect( Collectors.toList() );
  }

  public Optional<SubscriptionComponent> findBySubscriptionId( final String subscriptionId, final String componentId )
  {
    try
    {
      return Optional.of( chargify.httpClient()
                                  .getForObject( "/subscriptions/" + subscriptionId + "/components/" + componentId + ".json",
                                                 SubscriptionComponentWrapper.class )
                                  .getComponent() );
    }
    catch( ResourceNotFoundException e )
    {
      return Optional.empty();
    }
  }

  public Usage reportUsage( final String subscriptionId, final String componentId, final Usage usage )
  {
    return chargify.httpClient()
            .postForObject( "/subscriptions/" + subscriptionId + "/components/" + componentId + "/usages.json",
                            new UsageWrapper( usage ), UsageWrapper.class )
            .getUsage();
  }
}
