package com.chargify.model.wrappers;

import com.chargify.model.product.Product;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class ProductWrapper
{
  private final Product product;

  @JsonCreator
  public ProductWrapper( @JsonProperty( "product" ) Product wrappedProduct )
  {
    this.product = wrappedProduct;
  }

  public Product getProduct()
  {
    return product;
  }

  @Override
  public String toString()
  {
    return "ProductWrapper{" +
            "product=" + product +
            '}';
  }
}
