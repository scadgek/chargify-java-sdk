package com.chargify.api;

import com.chargify.exceptions.ResourceNotFoundException;
import com.chargify.model.Product;
import com.chargify.model.wrappers.ProductWrapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class Products
{
  private final Chargify chargify;

  Products( Chargify chargify )
  {
    this.chargify = chargify;
  }

  public Product create( final String productFamilyId, final Product product )
  {
    return chargify.httpClient()
            .postForObject( "/product_families/" + productFamilyId + "/products.json", new ProductWrapper( product ), ProductWrapper.class )
            .getProduct();
  }

  public Optional<Product> readById( final String id )
  {
    try
    {
      return Optional.of( chargify.httpClient()
                                  .getForObject( "/products/" + id + ".json", ProductWrapper.class )
                                  .getProduct() );
    }
    catch( ResourceNotFoundException e )
    {
      return Optional.empty();
    }
  }

  public Optional<Product> readByApiHandle( final String handle )
  {
    try
    {
      return Optional.of( chargify.httpClient()
                                  .getForObject( "/products/handle/" + handle + ".json", ProductWrapper.class )
                                  .getProduct() );
    }
    catch( ResourceNotFoundException e )
    {
      return Optional.empty();
    }
  }

  public List<Product> readAll()
  {
    return Arrays.stream( chargify.httpClient()
                                  .getForObject( "/products.json", ProductWrapper[].class ) )
            .map( ProductWrapper::getProduct )
            .collect( Collectors.toList() );
  }

  public List<Product> readAllByFamilyId( final String productFamilyId )
  {
    return Arrays.stream( chargify.httpClient()
                                  .getForObject( "/product_families/" + productFamilyId + "/products.json", ProductWrapper[].class ) )
            .map( ProductWrapper::getProduct )
            .collect( Collectors.toList() );
  }

  public Optional<Product> archive( final String productId )
  {
    try
    {
      return Optional.of( chargify.httpClient()
                                  .exchange( "/products/" + productId + ".json", HttpMethod.DELETE, HttpEntity.EMPTY, ProductWrapper.class )
                                  .getBody()
                                  .getProduct() );
    }
    catch( ResourceNotFoundException e )
    {
      return Optional.empty();
    }
  }
}
