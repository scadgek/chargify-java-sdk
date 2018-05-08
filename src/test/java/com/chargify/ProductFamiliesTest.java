package com.chargify;

import com.chargify.api.Chargify;
import com.chargify.exceptions.ApiHandleNotUniqueException;
import com.chargify.model.ProductFamily;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Random;

public class ProductFamiliesTest
{
  private static final Random random = new Random();

  private static final Chargify chargify = new Chargify( "<stub>", "<stub>" );

  private static ProductFamily existingProductFamily;

  @BeforeClass
  public static void setup()
  {
    existingProductFamily = chargify.productFamily()
            .create( new ProductFamily( randomName() ) );
  }

  @AfterClass
  public static void cleanup()
  {
    chargify.productFamily()
            .archive( existingProductFamily.getId() );
  }

  @Test( expected = ApiHandleNotUniqueException.class )
  public void productFamilyApiHandleShouldBeUnique()
  {
    chargify.productFamily()
            .create( existingProductFamily );
  }

  @Test
  public void productFamilyRetrievedByIdShouldHaveProperHandle()
  {
    final ProductFamily retrievedProductFamily = chargify.productFamily()
            .read( existingProductFamily.getId() );

    Assert.assertEquals( "Product family retrieved by ID has wrong product handle",
                         existingProductFamily.getHandle(), retrievedProductFamily.getHandle() );
  }

  @Test
  public void readAllShouldRetrieveAtLeastOneProductFamily()
  {
    Assert.assertTrue( "At least one product family should exist",
                       chargify.productFamily().readAll().size() > 0 );
  }

  private static String randomName()
  {
    final byte[] bytes = new byte[ 10 ];
    random.nextBytes( bytes );
    return new String( bytes, Charset.forName( "UTF-8" ) );
  }
}
