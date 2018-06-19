package com.chargify;

import com.chargify.exceptions.ChargifyException;
import com.chargify.model.Product;
import com.chargify.model.ProductFamily;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class ProductsTest extends ChargifyTest
{
  private static ProductFamily productFamilyUnderTest;
  private static Product productUnderTest;
  private static Product productWithHandleUnderTest;

  @BeforeClass
  public static void setup()
  {
    productFamilyUnderTest = chargify.createProductFamily( new ProductFamily( randomName() ) );

    productUnderTest = chargify.createProduct( productFamilyUnderTest.getId(),
                                               new Product( randomName(), 0, 1,
                                                            Product.IntervalUnit.month ) );

    final Product productWithHandle = new Product( randomName(), 0, 1, Product.IntervalUnit.month );
    productWithHandle.setHandle( randomName() );
    productWithHandleUnderTest = chargify.createProduct( productFamilyUnderTest.getId(), productWithHandle );
  }

  @AfterClass
  public static void cleanup()
  {
    chargify.archiveProductById( productUnderTest.getId() );
    chargify.archiveProductById( productWithHandleUnderTest.getId() );
    chargify.archiveProductFamilyById( productFamilyUnderTest.getId() );
  }

  @Test
  public void productShouldBeFoundById()
  {
    final Optional<Product> product = chargify.findProductById( productUnderTest.getId() );

    Assert.assertTrue( "Product not found", product.isPresent() );
  }

  @Test
  public void productShouldNotBeFoundByInvalidId()
  {
    final Optional<Product> product = chargify.findProductById( "nonexisting" );

    Assert.assertFalse( "Product should not be found by invalid ID", product.isPresent() );
  }

  @Test
  public void productShouldBeFoundByApiHandle()
  {
    final Optional<Product> product = chargify.findProductByApiHandle( productWithHandleUnderTest.getHandle() );

    Assert.assertTrue( "Product not found", product.isPresent() );
  }

  @Test
  public void productShouldNotBeFoundByInvalidApiHandle()
  {
    final Optional<Product> product = chargify.findProductByApiHandle( productUnderTest.getHandle() );

    Assert.assertFalse( "Product should not be found by invalid ID", product.isPresent() );
  }

  @Test
  public void readAllShouldRetrieveAtLeastOne()
  {
    final List<Product> products = chargify.findAllProducts();

    Assert.assertTrue( "At least one product should be present", products.size() > 0 );
  }

  @Test
  public void readAllByFamilyIdShouldRetrieveAtLeastOne()
  {
    final List<Product> familyProducts = chargify.findProductsByProductFamilyId( productFamilyUnderTest.getId() );

    Assert.assertTrue( "At least one product should be present in the family",
                       familyProducts.size() > 0 );
  }

  // TODO: see issue https://chargify.zendesk.com/hc/en-us/requests/69553
  @Test( expected = ChargifyException.class )
  public void readByNonExistingFamilyShouldThrowException()
  {
    chargify.findProductFamilyById( "nonexisting" );
  }

  @Test
  public void archiveNonExisting()
  {
    final Optional<Product> archivedProduct = chargify.archiveProductById( "nonexisting" );

    Assert.assertFalse( "Non existing product has been archived", archivedProduct.isPresent() );
  }
}
