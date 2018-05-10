package com.chargify.api;

import com.chargify.exceptions.ResourceNotFoundException;
import com.chargify.model.ProductFamily;
import com.chargify.model.wrappers.ProductFamilyWrapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

  public Optional<ProductFamily> read( String id )
  {
    try
    {
      return Optional.of( chargify.httpClient()
                                  .getForObject( "/product_families/" + id + ".json", ProductFamilyWrapper.class )
                                  .getProductFamily() );
    }
    catch( ResourceNotFoundException e )
    {
      return Optional.empty();
    }
  }

  public List<ProductFamily> readAll()
  {
    return Arrays.stream( chargify.httpClient()
                                  .getForObject( "/product_families.json", ProductFamilyWrapper[].class ) )
            .map( ProductFamilyWrapper::getProductFamily )
            .collect( Collectors.toList() );
  }

  public Optional<ProductFamily> archive( String id )
  {
    try
    {
      return Optional.of( chargify.httpClient()
                                  .exchange( "/product_families/" + id + ".json", HttpMethod.DELETE, HttpEntity.EMPTY, ProductFamilyWrapper.class )
                                  .getBody()
                                  .getProductFamily() );
    }
    catch( ResourceNotFoundException e )
    {
      return Optional.empty();
    }
  }
}
