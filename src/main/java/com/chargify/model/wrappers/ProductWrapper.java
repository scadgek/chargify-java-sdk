package com.chargify.model.wrappers;

import com.chargify.model.Product;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class ProductWrapper
{
  private final Product product;

  @JsonCreator
  public ProductWrapper(
          @JsonProperty( "product" ) final Product wrappedProduct )
  {
    this.product = wrappedProduct;
  }

  public Product getProduct()
  {
    return product;
  }
}
