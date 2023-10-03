package com.chargify.model.wrappers;

import com.chargify.model.product.Product;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public final class ProductWrapper
{
  private final Product product;

  @JsonCreator
  public ProductWrapper( @JsonProperty( "product" ) Product wrappedProduct )
  {
    this.product = wrappedProduct;
  }
}
