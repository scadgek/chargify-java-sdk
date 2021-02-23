package com.chargify.model.wrappers;

import com.chargify.model.product.ProductFamily;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class ProductFamilyWrapper
{
  private final ProductFamily productFamily;

  @JsonCreator
  public ProductFamilyWrapper( @JsonProperty( "product_family" ) ProductFamily wrappedProductFamily )
  {
    this.productFamily = wrappedProductFamily;
  }

  @JsonProperty( "product_family" )
  public ProductFamily getProductFamily()
  {
    return productFamily;
  }

  @Override
  public String toString()
  {
    return "ProductFamilyWrapper{" +
            "productFamily=" + productFamily +
            '}';
  }
}
