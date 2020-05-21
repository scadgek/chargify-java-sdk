package com.chargify;

import com.chargify.model.*;

import java.util.List;

public interface Chargify
{
  ProductFamily createProductFamily( ProductFamily productFamily );

  ProductFamily findProductFamilyById( String id );

  List<ProductFamily> findAllProductFamilies();

  ProductFamily archiveProductFamilyById( String id );

  Product createProduct( String productFamilyId, Product product );

  Product findProductById( String id );

  Product findProductByApiHandle( String apiHandle );

  List<Product> findAllProducts();

  List<Product> findProductsByProductFamilyId( String productFamilyId );

  Product archiveProductById( String id );

  Subscription createSubscription( CreateSubscription subscription );

  Subscription findSubscriptionById( String id );

  List<Subscription> findSubscriptionsByCustomerId( String customerId );

  List<Subscription> findAllSubscriptions();

  List<Subscription> findSubscriptionsByState( String state, int pageNumber, int pageSize );

  Subscription cancelSubscriptionById( String id );

  Subscription cancelSubscriptionProductChange( String subscriptionId );

  Subscription migrateSubscription( String subscriptionId, String productHandle );

  Subscription reactivateSubscription( String subscriptionId );

  ComponentPricePointUpdate migrateSubscriptionComponentToPricePoint( String subscriptionId, int componentId,
                                                                      String pricePointHandle );

  Subscription changeSubscriptionProduct( String subscriptionId, String productHandle, boolean delayed );

  RenewalPreview previewSubscriptionRenewal( String subscriptionId );

  List<Metadata> createSubscriptionMetadata( String subscriptionId, Metadata... metadata );

  SubscriptionMetadata readSubscriptionMetadata( String subscriptionId );

  List<Metadata> updateSubscriptionMetadata( String subscriptionId, Metadata... metadata );

  Component createComponent( String productFamilyId, Component component );

  Allocation createComponentAllocation( String subscriptionId, String componentId, Allocation allocation );

  List<Component> findComponentsByProductFamily( String productFamilyId );

  Component findComponentByIdAndProductFamily( String componentId, String productFamilyId );

  List<SubscriptionComponent> findSubscriptionComponents( String subscriptionId );

  SubscriptionComponent findSubscriptionComponentById( String subscriptionId, String componentId );

  Usage reportSubscriptionComponentUsage( String subscriptionId, String componentId, Usage usage );

  Customer createCustomer( Customer customer );

  Customer updateCustomer( Customer customer );

  Customer findCustomerById( String id );

  Customer findCustomerByReference( String reference );

  List<Customer> findAllCustomers();

  void deleteCustomerById( String id );

  ReferralCode validateReferralCode( String code );

  Adjustment adjust( String subscriptionId, Adjustment adjustment );
}
