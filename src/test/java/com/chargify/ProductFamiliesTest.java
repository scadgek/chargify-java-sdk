package com.chargify;

import com.chargify.exceptions.ApiHandleNotUniqueException;
import com.chargify.model.ProductFamily;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Optional;

public class ProductFamiliesTest extends ChargifyTest
{
  private static ProductFamily existingProductFamily;

  @BeforeClass
  public static void setup()
  {
    existingProductFamily = chargify.productFamilies()
            .create( new ProductFamily( randomName() ) );
  }

  @AfterClass
  public static void cleanup()
  {
    chargify.productFamilies()
            .archive( existingProductFamily.getId() );
  }

  @Test( expected = ApiHandleNotUniqueException.class )
  public void productFamilyApiHandleShouldBeUnique()
  {
    chargify.productFamilies()
            .create( existingProductFamily );
  }

  @Test
  public void productFamilyRetrievedByIdShouldHaveProperHandle()
  {
    final Optional<ProductFamily> retrievedProductFamily = chargify.productFamilies()
            .read( existingProductFamily.getId() );

    Assert.assertTrue( "Product family not found by ID " + existingProductFamily.getId(),
                       retrievedProductFamily.isPresent() );
    Assert.assertEquals( "Product family retrieved by ID has wrong product handle",
                         existingProductFamily.getHandle(), retrievedProductFamily.get().getHandle() );
  }

  @Test
  public void readAllShouldRetrieveAtLeastOneProductFamily()
  {
    Assert.assertTrue( "At least one product family should exist",
                       chargify.productFamilies().readAll().size() > 0 );
  }

  @Test
  public void readNonExistingShouldReturnEmptyOptional()
  {
    final Optional<ProductFamily> maybeProductFamily = chargify.productFamilies().read( "nonexisting" );

    Assert.assertFalse( "Non existing product family found", maybeProductFamily.isPresent() );
  }

  @Test
  public void archiveNonExistingShouldReturnEmptyOptional()
  {
    final Optional<ProductFamily> maybeProductFamily = chargify.productFamilies().archive( "nonexisting" );

    Assert.assertFalse( "Non existing product family found", maybeProductFamily.isPresent() );
  }
}
