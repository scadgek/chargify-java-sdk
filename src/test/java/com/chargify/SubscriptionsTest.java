package com.chargify;

import com.chargify.model.CreateSubscription;
import com.chargify.model.Customer;
import com.chargify.model.PricePointIntervalUnit;
import com.chargify.model.product.Product;
import com.chargify.model.product.ProductFamily;
import com.chargify.model.Subscription;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
    productFamilyUnderTest = chargify.createProductFamily( new ProductFamily( randomName() ) ).block();

    final Product product = new Product( randomName(), 0, 1, PricePointIntervalUnit.month );
    product.setRequestCreditCard( false );
    product.setRequireCreditCard( false );
    productUnderTest = chargify.createProduct( productFamilyUnderTest.getId(), product ).block();

    product.setHandle( randomName() );
    productForDelayedChange = chargify.createProduct( productFamilyUnderTest.getId(), product ).block();

    product.setHandle( randomName() );
    productForImmediateChange = chargify.createProduct( productFamilyUnderTest.getId(), product ).block();

    product.setHandle( randomName() );
    productForMigration = chargify.createProduct( productFamilyUnderTest.getId(), product ).block();

    customerUnderTest = chargify.createCustomer( new Customer( "Andy", "Panda",
                                                               "andypanda@example.com" ) ).block();

    final CreateSubscription subscription = new CreateSubscription();
    subscription.setProductId( productUnderTest.getId() );
    subscription.setCustomerId( customerUnderTest.getId() );
    subscriptionUnderTest = chargify.createSubscription( subscription ).block();
  }

  @AfterClass
  public static void cleanup()
  {
    chargify.cancelSubscriptionById( subscriptionUnderTest.getId() ).block();
    chargify.archiveProductById( productUnderTest.getId() ).block();
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
    final Subscription subscription = chargify.findSubscriptionById( subscriptionUnderTest.getId() ).block();
    assertNotNull( "Subscription not found", subscription );
  }

  @Test
  public void subscriptionShoudNotBeFoundByInvalidId()
  {
    final Subscription subscription = chargify.findSubscriptionById( "invalid" ).block();
    assertNull( "Subscription should not have been found", subscription );
  }

  @Test
  public void customerShouldHaveAtLeastOneSubscription()
  {
    final List<Subscription> subscriptions = chargify.findSubscriptionsByCustomerId( customerUnderTest.getId() ).collectList().block();
    assertTrue( "No subscriptions found for customer", subscriptions.size() > 0 );
  }

  @Test
  public void findAllShouldReturnAtLeastOne()
  {
    final List<Subscription> subscriptions = chargify.findAllSubscriptions().collectList().block();
    assertTrue( "No subscriptions found", subscriptions.size() > 0 );
  }

  @Test
  public void immediateProductChangeShouldChangeProduct()
  {
    final Subscription subscription = chargify.changeSubscriptionProduct( subscriptionUnderTest.getId(),
                                                                          productForImmediateChange.getHandle(),
                                                                          false ).block();
    assertNull( "Product change scheduled", subscription.getNextProductId() );
    assertEquals( "Product should have been changed", productForImmediateChange.getId(), subscription.getProduct().getId() );
  }

  @Test
  public void migrationShouldChangeProduct()
  {
    final Subscription subscription = chargify.migrateSubscription( subscriptionUnderTest.getId(),
                                                                    productForMigration.getHandle() ).block();
    assertNull( "Product change scheduled", subscription.getNextProductId() );
    assertEquals( "Product has not been migrated", productForMigration.getHandle(), subscription.getProduct().getHandle() );
  }
}
