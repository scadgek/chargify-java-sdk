package com.chargify;

import com.chargify.model.Allocation;
import com.chargify.model.ComponentCreateInput;
import com.chargify.model.ComponentDefinition;
import com.chargify.model.Customer;
import com.chargify.model.Migration;
import com.chargify.model.Product;
import com.chargify.model.RenewalPreview;
import com.chargify.model.SubscriptionComponent;
import com.chargify.model.SubscriptionInput;
import com.chargify.model.SubscriptionOutput;
import com.chargify.model.ValidationReferralCodeOutput;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface Chargify
{
  // Allocations API (https://reference.chargify.com/v1/allocations)

  Allocation createAllocation( final String subscriptionId, final String componentId, final int quantity,
                               final String memo );

  // Components API (https://reference.chargify.com/v1/components)

  Collection<SubscriptionComponent> listComponentsBySubscription( final String subscriptionId );

  ComponentDefinition readComponentByProductFamily( String productFamilyId, String componentId );

  SubscriptionComponent readComponentBySubscription( String subscriptionId, String componentId );

  // Customers API (https://reference.chargify.com/v1/customers)

  Customer createCustomer( Customer customer );

  Optional<Customer> readCustomerByReference( String reference );

  void deleteCustomer( String customerId );

  // Migrations API (https://reference.chargify.com/v1/migrations-prorated-upgrades-downgrades)

  SubscriptionOutput createMigration( String subscriptionId, Migration migration );

  // Products API (https://reference.chargify.com/v1/products)

  Collection<Product> listProductsByProductFamily( String productFamilyId );

  Optional<Product> readProductViaApiHandle( final String apiHandle );

  // Renewal Preview API (https://reference.chargify.com/v1/renewal-preview)

  RenewalPreview renewalPreview( final String subscriptionId );

  // Subscriptions API (https://reference.chargify.com/v1/subscriptions)

  SubscriptionOutput createSubscription( SubscriptionInput subscription );

  Optional<SubscriptionOutput> readSubscription( String subscriptionId );

  List<SubscriptionOutput> listSubscriptionsByCustomer( String customerId );

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
  List<SubscriptionOutput> listSubscriptionsByState( String state, int page, int pageSize );

  /**
   * @param state The current state of the subscription.
   *              Allowed Values: active, canceled, expired, expired_cards, on_hold, past_due,
   *              pending_cancellation, pending_renewal, suspended, trial_ended, trialing, unpaid
   * @return number of subscriptions
   */
  int countSubscriptionsByState( String state );

  /**
   * @return number moved of subscriptions
   */
  int moveTrialEndedToFree();

  Optional<SubscriptionOutput> cancelSubscription( String subscriptionId );

  ValidationReferralCodeOutput validateReferralCode( String referralCode );

  ComponentDefinition createComponent( String productFamilyId, ComponentCreateInput componentCreateInput );
}
