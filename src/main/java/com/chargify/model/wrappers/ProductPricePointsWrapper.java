package com.chargify.model.wrappers;

import com.chargify.model.product.ProductPricePoint;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@ToString
public final class ProductPricePointsWrapper
{
  @Getter
  private final Set<ProductPricePoint> pricePoints;

  @JsonCreator
  public ProductPricePointsWrapper( @JsonProperty( "price_points" ) Set<ProductPricePoint> pricePoints )
  {
    this.pricePoints = pricePoints;
  }
}
