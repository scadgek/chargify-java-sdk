package com.chargify;

import com.chargify.model.Customer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CustomersTest extends ChargifyTest
{
  private static Customer customerUnderTest;
  private static Customer customerWithReferenceUnderTest;

  @BeforeClass
  public static void setup()
  {
    customerUnderTest = chargify.createCustomer( new Customer( "Andy", "Panda",
                                                               "andypanda@email.com" ) );

    final Customer customer = new Customer( "Martha", "Washington", "martha@example.com" );
    customer.setReference( randomName() );
    customerWithReferenceUnderTest = chargify.createCustomer( customer );
  }

  @AfterClass
  public static void cleanup()
  {
    chargify.deleteCustomerById( customerUnderTest.getId() );
    chargify.deleteCustomerById( customerWithReferenceUnderTest.getId() );
  }

  @Test
  public void customerShouldBeFoundByValidId()
  {
    final Optional<Customer> customer = chargify.findCustomerById( customerUnderTest.getId() );
    assertTrue( "Customer not found", customer.isPresent() );
  }

  @Test
  public void customerShouldNotBeFoundByInvalidId()
  {
    final Optional<Customer> customer = chargify.findCustomerById( "nonexisting" );
    assertFalse( "Customer should not have been found", customer.isPresent() );
  }

  @Test
  public void findAllShouldFindAtLeastOne()
  {
    final List<Customer> customers = chargify.findAllCustomers();
    assertTrue( "No customers found", customers.size() > 0 );
  }

  @Test
  public void customerShouldBeFoundByValidReference()
  {
    final Optional<Customer> customer = chargify.findCustomerByReference( customerWithReferenceUnderTest.getReference() );
    assertTrue( "Customer not found by reference", customer.isPresent() );
  }

  @Test
  public void customerShouldNotBeFoundByInvalidReference()
  {
    final Optional<Customer> customer = chargify.findCustomerByReference( "invalid" );
    assertFalse( "Customer should not have been found", customer.isPresent() );
  }
}
