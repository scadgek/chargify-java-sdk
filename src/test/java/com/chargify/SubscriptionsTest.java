package com.chargify;

import com.chargify.model.Customer;
import com.chargify.model.Product;
import com.chargify.model.ProductFamily;
import com.chargify.model.Subscription;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class SubscriptionsTest extends ChargifyTest
{
  private static ProductFamily productFamilyUnderTest;
  private static Product productUnderTest;
  private static Customer customerUnderTest;
  private static Subscription subscriptionUnderTest;
  private static Product productForDelayedChange;
  private static Product productForImmediateChange;
  private static Product productForMigration;

  @BeforeClass
  public static void setup()
  {
    productFamilyUnderTest = chargify.createProductFamily( new ProductFamily( randomName() ) );

    final Product product = new Product( randomName(), 0, 1, Product.IntervalUnit.month );
    product.setRequestCreditCard( false );
    product.setRequireCreditCard( false );
    productUnderTest = chargify.createProduct( productFamilyUnderTest.getId(), product );

    product.setHandle( randomName() );
    productForDelayedChange = chargify.createProduct( productFamilyUnderTest.getId(), product );

    product.setHandle( randomName() );
    productForImmediateChange = chargify.createProduct( productFamilyUnderTest.getId(), product );

    product.setHandle( randomName() );
    productForMigration = chargify.createProduct( productFamilyUnderTest.getId(), product );

    customerUnderTest = chargify.createCustomer( new Customer( "Andy", "Panda",
                                                               "andypanda@example.com" ) );

    final Subscription subscription = new Subscription();
    subscription.setProductId( productUnderTest.getId() );
    subscription.setCustomerId( customerUnderTest.getId() );
    subscriptionUnderTest = chargify.createSubscription( subscription );
  }

  @AfterClass
  public static void cleanup()
  {
    chargify.cancelSubscriptionById( subscriptionUnderTest.getId() );
    chargify.archiveProductById( productUnderTest.getId() );
    // cannot be archived - subscription change pending
//    chargify.archiveProductById( productForDelayedChange.getId() );
    // cannot be archived - product still exists
//    chargify.archiveProductFamilyById( productFamilyUnderTest.getId() );
    // cannot be deleted while subscription exists - requires manual deletion from console
//    chargify.deleteCustomerById( customerUnderTest.getId() );
  }

  @Test
  public void subscriptionShouldBeFoundByValidId()
  {
    final Optional<Subscription> subscription = chargify.findSubscriptionById( subscriptionUnderTest.getId() );
    assertTrue( "Subscription not found", subscription.isPresent() );
  }

  @Test
  public void subscriptionShoudNotBeFoundByInvalidId()
  {
    final Optional<Subscription> subscription = chargify.findSubscriptionById( "invalid" );
    assertFalse( "Subscription should not have been found", subscription.isPresent() );
  }

  @Test
  public void customerShouldHaveAtLeastOneSubscription()
  {
    final List<Subscription> subscriptions = chargify.findSubscriptionsByCustomerId( customerUnderTest.getId() );
    assertTrue( "No subscriptions found for customer", subscriptions.size() > 0 );
  }

  @Test
  public void findAllShouldReturnAtLeastOne()
  {
    final List<Subscription> subscriptions = chargify.findAllSubscriptions();
    assertTrue( "No subscriptions found", subscriptions.size() > 0 );
  }

  @Test
  public void immediateProductChangeShouldChangeProduct()
  {
    final Subscription subscription = chargify.changeSubscriptionProduct( subscriptionUnderTest.getId(),
                                                                          productForImmediateChange.getHandle(),
                                                                          false );
    assertNull( "Product change scheduled", subscription.getNextProductId() );
    assertEquals( "Product should have been changed", productForImmediateChange.getId(), subscription.getProduct().getId() );
  }

  @Test
  public void migrationShouldChangeProduct()
  {
    final Subscription subscription = chargify.migrateSubscription( subscriptionUnderTest.getId(),
                                                                    productForMigration.getHandle() );
    assertNull( "Product change scheduled", subscription.getNextProductId() );
    assertEquals( "Product has not been migrated", productForMigration.getHandle(), subscription.getProduct().getHandle() );
  }
}
