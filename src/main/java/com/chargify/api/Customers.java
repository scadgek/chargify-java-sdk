package com.chargify.api;

import com.chargify.exceptions.ResourceNotFoundException;
import com.chargify.model.Customer;
import com.chargify.model.wrappers.CustomerWrapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class Customers
{
  private final Chargify chargify;

  Customers( Chargify chargify )
  {
    this.chargify = chargify;
  }

  public Customer create( final Customer customer )
  {
    return chargify.httpClient()
            .postForObject( "/customers.json", new CustomerWrapper( customer ), CustomerWrapper.class )
            .getCustomer();
  }

  public Optional<Customer> findById( final String id )
  {
    try
    {
      return Optional.of( chargify.httpClient()
                                  .getForObject( "/customers/" + id + ".json", CustomerWrapper.class )
                                  .getCustomer() );
    }
    catch( ResourceNotFoundException e )
    {
      return Optional.empty();
    }
  }

  public Optional<Customer> findByReference( final String reference )
  {
    try
    {
      return Optional.of( chargify.httpClient()
                                  .getForObject( "/customers/lookup.json?reference={reference}",
                                                 CustomerWrapper.class,
                                                 reference )
                                  .getCustomer() );
    }
    catch( ResourceNotFoundException e )
    {
      return Optional.empty();
    }
  }

  public List<Customer> findAll()
  {
    return Arrays.stream( chargify.httpClient()
                                  .getForObject( "/customers.json", CustomerWrapper[].class ) )
            .map( CustomerWrapper::getCustomer )
            .collect( Collectors.toList() );
  }

  public void delete( final String id )
  {
    chargify.httpClient().delete( "/customers/" + id + ".json" );
  }

  public void deleteIfExists( final String id )
  {
    try
    {
      delete( id );
    }
    catch( ResourceNotFoundException ignored )
    {
    }
  }
}
