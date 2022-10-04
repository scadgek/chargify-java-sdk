package com.chargify;

import com.chargify.model.*;
import com.chargify.model.product.Product;
import com.chargify.model.product.ProductFamily;
import com.chargify.model.product.ProductPricePoint;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

public interface Chargify
{
  Mono<ProductFamily> createProductFamily( ProductFamily productFamily );

  Mono<ProductFamily> findProductFamilyById( String id );

  Flux<ProductFamily> findAllProductFamilies();

  Mono<ProductFamily> archiveProductFamilyById( String id );

  Mono<Product> createProduct( String productFamilyId, Product product );

  Mono<Product> findProductById( String id );

  Mono<Product> findProductByApiHandle( String apiHandle );

  Flux<ProductPricePoint> findProductPricePointsByProductId( String productId );

  Flux<PricePoint> findComponentPricePoints( int componentId );

  Flux<Product> findAllProducts();

  Flux<Product> findProductsByProductFamilyId( String productFamilyId );

  Mono<Product> archiveProductById( String id );

  Mono<Subscription> createSubscription( CreateSubscription subscription );

  Mono<Void> updateSubscription( String subscriptionId, UpdateSubscription subscription );

  Mono<Void> updateSubscriptionNextBillingDate( String subscriptionId, LocalDateTime nextBillingDate );

  Mono<SubscriptionChargeResult> createSubscriptionCharge( String subscriptionId, SubscriptionCharge subscriptionCharge );

  Mono<Subscription> findSubscriptionById( String id );

  Flux<PaymentProfile> findPaymentProfilesForCustomer( String customerId );

  Mono<PaymentProfile> createPaymentProfile( CreatePaymentProfile paymentProfile );

  Mono<Void> updatePaymentProfile( String paymentProfileId, UpdatePaymentProfile paymentProfile );

  Mono<PaymentProfile> updateSubscriptionPaymentProfile( String subscriptionId, String paymentProfileId );

  Mono<PaymentProfile> findPaymentProfileById( String paymentProfileId );

  Mono<Void> deleteUnusedPaymentProfile( String paymentProfileId );

  Mono<Void> deletePaymentProfile( String subscriptionId, String paymentProfileId );

  Flux<Subscription> findSubscriptionsByCustomerId( String customerId );

  Flux<Subscription> findSubscriptionsByCustomerId( String customerId, int pageNumber, int pageSize );

  Flux<Subscription> findAllSubscriptions();

  Mono<Subscription> purgeSubscription( Subscription subscription );

  Flux<Subscription> findSubscriptionsByState( String state, int pageNumber, int pageSize );

  Mono<Subscription> cancelSubscriptionById( String id );

  Mono<Subscription> cancelSubscriptionProductChange( String subscriptionId );

  Mono<Subscription> migrateSubscription( String subscriptionId, String productHandle );

  Mono<Subscription> migrateSubscription( String subscriptionId, String productHandle, String pricePointHandle );

  Mono<Subscription> reactivateSubscription( String subscriptionId, boolean preserveBalance );

  Mono<Subscription> reactivateSubscription( String subscriptionId, SubscriptionReactivationData reactivationData );

  Mono<ComponentPricePointUpdate> migrateSubscriptionComponentToPricePoint( String subscriptionId, int componentId,
                                                                      String pricePointHandle );

  Flux<ComponentPricePointUpdate> bulkUpdateSubscriptionComponentPricePoint( String subscriptionId, List<ComponentPricePointUpdate> items );

  Mono<Subscription> cancelScheduledSubscriptionProductChange( String subscriptionId );

  Mono<Subscription> changeSubscriptionProduct( String subscriptionId, String productHandle, boolean delayed );

  Mono<Subscription> changeSubscriptionProduct( String subscriptionId, String productHandle, String pricePointHandle, boolean delayed );

  Mono<RenewalPreview> previewSubscriptionRenewal( String subscriptionId );

  Flux<Metadata> createSubscriptionMetadata( String subscriptionId, Metadata... metadata );

  Mono<SubscriptionMetadata> readSubscriptionMetadata( String subscriptionId );

  Flux<Metadata> updateSubscriptionMetadata( String subscriptionId, Metadata... metadata );

  Mono<Component> createComponent( String productFamilyId, Component component );

  Mono<Allocation> createComponentAllocation( String subscriptionId, int componentId, Allocation allocation );

  Mono<AllocationPreview> previewComponentAllocation( String subscriptionId, int componentId, int quantity );

  Flux<Component> findComponentsByProductFamily( String productFamilyId );

  Mono<Component> findComponentByIdAndProductFamily( int componentId, String productFamilyId );

  Mono<ComponentWithPricePoints> findComponentWithPricePointsByIdAndProductFamily( int componentId, String productFamilyId );

  Flux<SubscriptionComponent> findSubscriptionComponents( String subscriptionId );

  Flux<SubscriptionStatement> findSubscriptionStatements(
          String subscriptionId, int page, int pageSize, String sort, String direction );

  Mono<SubscriptionComponent> findSubscriptionComponentById( String subscriptionId, int componentId );

  Mono<Usage> reportSubscriptionComponentUsage( String subscriptionId, int componentId, Usage usage );

  Mono<Customer> createCustomer( Customer customer );

  Mono<Customer> updateCustomer( Customer customer );

  Mono<Customer> findCustomerById( String id );

  Mono<Customer> findCustomerByReference( String reference );

  Mono<Subscription> findSubscriptionByReference( String reference );

  /**
   * Search to retrieve a single or group of customers.
   *
   * @param criterion (string or integer) - can be email, Chargify ID, Reference (Your App), Organization
   * @param pageNumber (start from 1) the page parameter via the query string to access subsequent pages of 50 transactions
   * @return List of customers
   */
  Flux<Customer> findCustomersBy( Object criterion, int pageNumber );

  Flux<Customer> findAllCustomers();

  Mono<Void> deleteCustomerById( String id );

  Mono<ReferralCode> validateReferralCode( String code );

  Mono<Adjustment> adjust( String subscriptionId, Adjustment adjustment );
}
