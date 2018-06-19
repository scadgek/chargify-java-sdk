package com.chargify;

import com.chargify.model.Allocation;
import com.chargify.model.Component;
import com.chargify.model.ComponentPricePointUpdate;
import com.chargify.model.Customer;
import com.chargify.model.Product;
import com.chargify.model.ProductFamily;
import com.chargify.model.ReferralCode;
import com.chargify.model.RenewalPreview;
import com.chargify.model.Subscription;
import com.chargify.model.SubscriptionComponent;
import com.chargify.model.Usage;

import java.util.List;
import java.util.Optional;

public interface Chargify
{
  ProductFamily createProductFamily( ProductFamily productFamily );

  Optional<ProductFamily> findProductFamilyById( String id );

  List<ProductFamily> findAllProductFamilies();

  Optional<ProductFamily> archiveProductFamilyById( String id );

  Product createProduct( String productFamilyId, Product product );

  Optional<Product> findProductById( String id );

  Optional<Product> findProductByApiHandle( String apiHandle );

  List<Product> findAllProducts();

  List<Product> findProductsByProductFamilyId( String productFamilyId );

  Optional<Product> archiveProductById( String id );

  Subscription createSubscription( Subscription subscription );

  Optional<Subscription> findSubscriptionById( String id );

  List<Subscription> findSubscriptionsByCustomerId( String customerId );

  List<Subscription> findAllSubscriptions();

  List<Subscription> findSubscriptionsByState( String state, int pageNumber, int pageSize );

  Optional<Subscription> cancelSubscriptionById( String id );

  Subscription cancelSubscriptionProductChange( String subscriptionId );

  Subscription migrateSubscription( String subscriptionId, String productHandle );

  ComponentPricePointUpdate migrateSubscriptionComponentToPricePoint( String subscriptionId, int componentId,
                                                                      String pricePointHandle );

  Subscription changeSubscriptionProduct( String subscriptionId, String productHandle, boolean delayed );

  RenewalPreview previewSubscriptionRenewal( String subscriptionId );

  Component createComponent( String productFamilyId, Component component );

  Allocation createComponentAllocation( String subscriptionId, String componentId, int quantity, String memo );

  List<Component> findComponentsByProductFamily( String productFamilyId );

  Optional<Component> findComponentByIdAndProductFamily( String componentId, String productFamilyId );

  List<SubscriptionComponent> findSubscriptionComponents( String subscriptionId );

  Optional<SubscriptionComponent> findSubscriptionComponentById( String subscriptionId, String componentId );

  Usage reportSubscriptionComponentUsage( String subscriptionId, String componentId, Usage usage );

  Customer createCustomer( Customer customer );

  Optional<Customer> findCustomerById( String id );

  Optional<Customer> findCustomerByReference( String reference );

  List<Customer> findAllCustomers();

  void deleteCustomerById( String id );

  Optional<ReferralCode> validateReferralCode( String code );
}
