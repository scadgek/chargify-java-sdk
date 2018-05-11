package com.chargify.api;

import com.chargify.model.Component;
import com.chargify.model.SubscriptionComponent;
import com.chargify.model.wrappers.AnyComponentWrapper;
import com.chargify.model.wrappers.ComponentWrapper;
import com.chargify.model.wrappers.MeteredComponentWrapper;
import com.chargify.model.wrappers.OnOffComponentWrapper;
import com.chargify.model.wrappers.QuantityBasedComponentWrapper;
import com.chargify.model.wrappers.SubscriptionComponentWrapper;

import java.util.Arrays;
import java.util.List;
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

  public List<Component> findByProductFamily( final String productFamilyId )
  {
    return Arrays.stream( chargify.httpClient()
                                  .getForObject( "/product_families/" + productFamilyId + "/components.json",
                                                 AnyComponentWrapper[].class ) )
            .map( AnyComponentWrapper::getComponent )
            .collect( Collectors.toList() );
  }

  public List<SubscriptionComponent> findBySubscriptionId( final String subscriptionId )
  {
    return Arrays.stream( chargify.httpClient()
                                  .getForObject( "/subscriptions/" + subscriptionId + "/components.json", SubscriptionComponentWrapper[].class ) )
            .map( SubscriptionComponentWrapper::getComponent )
            .collect( Collectors.toList() );
  }
}
