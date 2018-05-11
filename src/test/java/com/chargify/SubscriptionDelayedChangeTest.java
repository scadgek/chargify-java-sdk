package com.chargify;

import com.chargify.model.Customer;
import com.chargify.model.Product;
import com.chargify.model.ProductFamily;
import com.chargify.model.Subscription;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SubscriptionDelayedChangeTest extends ChargifyTest
{
  private static ProductFamily productFamily;
  private static Product initialProduct;
  private static Product targetProduct;
  private static Subscription subscription;
  private static Customer customer;

  @BeforeClass
  public static void setup()
  {
    productFamily = chargify.productFamilies().create( new ProductFamily( randomName() ) );

    final Product initialProduct = new Product( randomName(), 0, 1, Product.IntervalUnit.month );
    initialProduct.setRequestCreditCard( false );
    initialProduct.setRequireCreditCard( false );
    SubscriptionDelayedChangeTest.initialProduct = chargify.products().create( productFamily.getId(), initialProduct );

    final Product targetProduct = new Product( randomName(), 0, 1, Product.IntervalUnit.month );
    targetProduct.setRequestCreditCard( false );
    targetProduct.setRequireCreditCard( false );
    targetProduct.setHandle( randomName() );
    SubscriptionDelayedChangeTest.targetProduct = chargify.products().create( productFamily.getId(), targetProduct );

    customer = chargify.customers().create( new Customer( randomName(), randomName(), randomEmail() ) );

    final Subscription subscription = new Subscription();
    subscription.setProductId( SubscriptionDelayedChangeTest.initialProduct.getId() );
    subscription.setCustomerId( customer.getId() );
    SubscriptionDelayedChangeTest.subscription = chargify.subscriptions().create( subscription );
  }

  @Test
  public void delayedProductChangeShouldModifyNextProductIdAndNotChangeCurrentProduct()
  {
    final Subscription resultSubscription = chargify.subscriptions().productChange( subscription.getId(), targetProduct.getHandle(), true );
    assertNotNull( "Product change not scheduled", resultSubscription.getNextProductId() );
    assertEquals( "Scheduled change to wrong product", targetProduct.getId(), resultSubscription.getNextProductId() );
    assertEquals( "Current product changed", initialProduct.getId(), resultSubscription.getProduct().getId() );
  }

  @AfterClass
  public static void cleanup()
  {
    chargify.subscriptions().cancelProductChange( subscription.getId() );
    chargify.subscriptions().cancel( subscription.getId() );
    chargify.products().archive( targetProduct.getId() );
    chargify.products().archive( initialProduct.getId() );
    chargify.productFamilies().archive( productFamily.getId() );
    // The customer could not be deleted because there are subscriptions associated with this account. You may want to delete the individual subscriptions first.
//    chargify.customers().delete( customer.getId() );
  }
}
