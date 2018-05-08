package com.chargify.api;

import com.chargify.model.ProductFamily;
import com.chargify.model.wrappers.ProductFamilyWrapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ProductFamilies
{
  private final Chargify chargify;

  ProductFamilies( Chargify chargify )
  {
    this.chargify = chargify;
  }

  public ProductFamily create( ProductFamily productFamily )
  {
    return chargify.httpClient()
            .postForObject( "/product_families.json", new ProductFamilyWrapper( productFamily ), ProductFamilyWrapper.class )
            .getProductFamily();
  }

  public ProductFamily read( String id )
  {
    return chargify.httpClient()
            .getForObject( "/product_families/" + id + ".json", ProductFamilyWrapper.class )
            .getProductFamily();
  }

  public List<ProductFamily> readAll()
  {
    return Stream.of( chargify.httpClient()
                              .getForObject( "/product_families.json", ProductFamilyWrapper[].class ) )
            .map( ProductFamilyWrapper::getProductFamily )
            .collect( Collectors.toList() );
  }

  public ProductFamily archive( String id )
  {
    return chargify.httpClient()
            .exchange( "/product_families/" + id + ".json", HttpMethod.DELETE, HttpEntity.EMPTY, ProductFamilyWrapper.class )
            .getBody()
            .getProductFamily();
  }
}
