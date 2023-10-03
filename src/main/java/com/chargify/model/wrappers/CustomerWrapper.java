package com.chargify.model.wrappers;

import com.chargify.model.Customer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public final class CustomerWrapper
{
  private final Customer customer;

  @JsonCreator
  public CustomerWrapper( @JsonProperty( "customer" ) Customer wrappedCustomer )
  {
    this.customer = wrappedCustomer;
  }
}
