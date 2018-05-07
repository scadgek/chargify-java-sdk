package com.chargify.model.wrappers;

import com.chargify.model.Customer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class CustomerWrapper
{
  private final Customer customer;

  @JsonCreator
  public CustomerWrapper(
          @JsonProperty( "customer" )
                  Customer wrappedCustomer )
  {
    this.customer = wrappedCustomer;
  }

  public Customer getCustomer()
  {
    return customer;
  }
}
