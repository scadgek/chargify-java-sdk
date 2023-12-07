package com.chargify;

import com.chargify.exceptions.ApiHandleNotUniqueException;
import com.chargify.model.product.ProductFamily;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProductFamiliesTest extends ChargifyTest
{
  private static ProductFamily existingProductFamily;

  @BeforeClass
  public static void setup()
  {
    existingProductFamily = chargify.createProductFamily( new ProductFamily( randomName() ) ).block();
  }

  @AfterClass
  public static void cleanup()
  {
    chargify.archiveProductFamilyById( existingProductFamily.getId() ).block();
  }

  @Test( expected = ApiHandleNotUniqueException.class )
  public void productFamilyApiHandleShouldBeUnique()
  {
    chargify.createProductFamily( existingProductFamily ).block();
  }

  @Test
  public void productFamilyRetrievedByIdShouldHaveProperHandle()
  {
    final ProductFamily retrievedProductFamily = chargify.findProductFamilyById( existingProductFamily.getId() ).block();

    Assert.assertNotNull( "Product family not found by ID " + existingProductFamily.getId(),
                          retrievedProductFamily );
    Assert.assertEquals( "Product family retrieved by ID has wrong product handle",
                         existingProductFamily.getHandle(), retrievedProductFamily.getHandle() );
  }

  @Test
  public void readAllShouldRetrieveAtLeastOneProductFamily()
  {
    Assert.assertFalse( "At least one product family should exist", chargify.findAllProductFamilies().collectList().block().isEmpty() );
  }

  @Test
  public void readNonExistingShouldReturnEmptyOptional()
  {
    final ProductFamily productFamily = chargify.findProductFamilyById( "nonexistent" ).block();

    Assert.assertNull( "Non existing product family found", productFamily );
  }

  @Test
  public void archiveNonExistingShouldReturnEmptyOptional()
  {
    final ProductFamily archivedProductFamily = chargify.archiveProductFamilyById( "nonexistent" ).block();

    Assert.assertNull( "Non existing product family found", archivedProductFamily );
  }
}
