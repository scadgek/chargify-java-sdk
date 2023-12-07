package com.chargify;

import com.chargify.model.PricePointIntervalUnit;
import com.chargify.model.product.Product;
import com.chargify.model.product.ProductFamily;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class ProductsTest extends ChargifyTest
{
  private static ProductFamily productFamilyUnderTest;
  private static Product productUnderTest;
  private static Product productWithHandleUnderTest;

  @BeforeClass
  public static void setup()
  {
    productFamilyUnderTest = chargify.createProductFamily( new ProductFamily( randomName() ) ).block();

    productUnderTest = chargify.createProduct( productFamilyUnderTest.getId(),
                                               new Product( randomName(), 0, 1,
                                                            PricePointIntervalUnit.month ) ).block();

    final Product productWithHandle = new Product( randomName(), 0, 1, PricePointIntervalUnit.month );
    productWithHandle.setHandle( randomName() );
    productWithHandleUnderTest = chargify.createProduct( productFamilyUnderTest.getId(), productWithHandle ).block();
  }

  @AfterClass
  public static void cleanup()
  {
    chargify.archiveProductById( productUnderTest.getId() ).block();
    chargify.archiveProductById( productWithHandleUnderTest.getId() ).block();
    chargify.archiveProductFamilyById( productFamilyUnderTest.getId() ).block();
  }

  @Test
  public void productShouldBeFoundById()
  {
    final Product product = chargify.findProductById( productUnderTest.getId() ).block();

    Assert.assertNotNull( "Product not found", product );
  }

  @Test
  public void productShouldNotBeFoundByInvalidId()
  {
    final Product product = chargify.findProductById( "nonexistent" ).block();

    Assert.assertNull( "Product should not be found by invalid ID", product );
  }

  @Test
  public void productShouldBeFoundByApiHandle()
  {
    final Product product = chargify.findProductByApiHandle( productWithHandleUnderTest.getHandle() ).block();

    Assert.assertNotNull( "Product not found", product );
  }

  @Test
  public void productShouldNotBeFoundByInvalidApiHandle()
  {
    final Product product = chargify.findProductByApiHandle( productUnderTest.getHandle() ).block();

    Assert.assertNull( "Product should not be found by invalid ID", product );
  }

  @Test
  public void readAllShouldRetrieveAtLeastOne()
  {
    final List<Product> products = chargify.findAllProducts().collectList().block();

    Assert.assertFalse( "At least one product should be present", products.isEmpty() );
  }

  @Test
  public void readAllByFamilyIdShouldRetrieveAtLeastOne()
  {
    final List<Product> familyProducts = chargify.findProductsByProductFamilyId( productFamilyUnderTest.getId() ).collectList().block();

    Assert.assertFalse( "At least one product should be present in the family", familyProducts.isEmpty() );
  }

  @Test
  public void readByNonExistingFamilyShouldReturnNull()
  {
    Assert.assertNull( chargify.findProductFamilyById( "nonexistent" ).block() );
  }

  @Test
  public void archiveNonExisting()
  {
    final Product archivedProduct = chargify.archiveProductById( "nonexistent" ).block();

    Assert.assertNull( "Non existing product has been archived", archivedProduct );
  }
}
