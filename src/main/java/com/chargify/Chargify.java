package com.chargify;

import com.chargify.model.Allocation;
import com.chargify.model.Component;
import com.chargify.model.ComponentPricePointUpdate;
import com.chargify.model.Customer;
import com.chargify.model.Metadata;
import com.chargify.model.Product;
import com.chargify.model.ProductFamily;
import com.chargify.model.ReferralCode;
import com.chargify.model.RenewalPreview;
import com.chargify.model.Subscription;
import com.chargify.model.SubscriptionComponent;
import com.chargify.model.SubscriptionMetadata;
import com.chargify.model.Usage;

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

  Subscription createSubscription( Subscription subscription );

  Subscription findSubscriptionById( String id );

  List<Subscription> findSubscriptionsByCustomerId( String customerId );

  List<Subscription> findAllSubscriptions();

  List<Subscription> findSubscriptionsByState( String state, int pageNumber, int pageSize );

  Subscription cancelSubscriptionById( String id );

  Subscription cancelSubscriptionProductChange( String subscriptionId );

  Subscription migrateSubscription( String subscriptionId, String productHandle );

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
}
