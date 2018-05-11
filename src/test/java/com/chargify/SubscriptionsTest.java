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
    productFamilyUnderTest = chargify.productFamilies()
            .create( new ProductFamily( randomName() ) );

    final Product product = new Product( randomName(), 0, 1, Product.IntervalUnit.month );
    product.setRequestCreditCard( false );
    product.setRequireCreditCard( false );
    productUnderTest = chargify.products()
            .create( productFamilyUnderTest.getId(), product );

    product.setHandle( randomName() );
    productForDelayedChange = chargify.products().create( productFamilyUnderTest.getId(), product );

    product.setHandle( randomName() );
    productForImmediateChange = chargify.products().create( productFamilyUnderTest.getId(), product );

    product.setHandle( randomName() );
    productForMigration = chargify.products().create( productFamilyUnderTest.getId(), product );

    customerUnderTest = chargify.customers()
            .create( new Customer( "Andy", "Panda", "andypanda@example.com" ) );

    final Subscription subscription = new Subscription();
    subscription.setProductId( productUnderTest.getId() );
    subscription.setCustomerId( customerUnderTest.getId() );
    subscriptionUnderTest = chargify.subscriptions().create( subscription );
  }

  @AfterClass
  public static void cleanup()
  {
    chargify.subscriptions().cancel( subscriptionUnderTest.getId() );
    chargify.products().archive( productUnderTest.getId() );
    // cannot be archived - subscription change pending
//    chargify.products().archive( productForDelayedChange.getId() );
    // cannot be archived - product still exists
//    chargify.productFamilies().archive( productFamilyUnderTest.getId() );
    // cannot be deleted while subscription exists - requires manual deletion from console
//    chargify.customers().deleteIfExists( customerUnderTest.getId() );
  }

  @Test
  public void subscriptionShouldBeFoundByValidId()
  {
    final Optional<Subscription> subscription = chargify.subscriptions().findById( subscriptionUnderTest.getId() );
    assertTrue( "Subscription not found", subscription.isPresent() );
  }

  @Test
  public void subscriptionShoudNotBeFoundByInvalidId()
  {
    final Optional<Subscription> subscription = chargify.subscriptions().findById( "invalid" );
    assertFalse( "Subscription should not have been found", subscription.isPresent() );
  }

  @Test
  public void customerShouldHaveAtLeastOneSubscription()
  {
    final List<Subscription> subscriptions = chargify.subscriptions().findByCustomerId( customerUnderTest.getId() );
    assertTrue( "No subscriptions found for customer", subscriptions.size() > 0 );
  }

  @Test
  public void findAllShouldReturnAtLeastOne()
  {
    final List<Subscription> subscriptions = chargify.subscriptions().findAll();
    assertTrue( "No subscriptions found", subscriptions.size() > 0 );
  }

  @Test
  public void immediateProductChangeShouldChangeProduct()
  {
    final Subscription subscription = chargify.subscriptions().productChange( subscriptionUnderTest.getId(), productForImmediateChange.getHandle(), false );
    assertNull( "Product change scheduled", subscription.getNextProductId() );
    assertEquals( "Product should have been changed", productForImmediateChange.getId(), subscription.getProduct().getId() );
  }

  @Test
  public void delayedProductChangeShouldChangeNextProductId()
  {
    final Subscription subscription = chargify.subscriptions().productChange( subscriptionUnderTest.getId(), productForDelayedChange.getHandle(), true );
    assertNotNull( "Product change not scheduled", subscription.getNextProductId() );
    assertEquals( "Wrong next product", productForDelayedChange.getId(), subscription.getNextProductId() );
  }

  @Test
  public void migrationShouldChangeProduct()
  {
    final Subscription subscription = chargify.subscriptions().migrate( subscriptionUnderTest.getId(), productForMigration.getHandle() );
    assertNull( "Product change scheduled", subscription.getNextProductId() );
    assertEquals( "Product has not been migrated", productForMigration.getHandle(), subscription.getProduct().getHandle() );
  }
}
