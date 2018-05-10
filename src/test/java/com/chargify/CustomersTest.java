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
    customerUnderTest = chargify.customers().create( new Customer( "Andy", "Panda", "andypanda@email.com" ) );

    final Customer customer = new Customer( "Martha", "Washington", "martha@example.com" );
    customer.setReference( randomName() );
    customerWithReferenceUnderTest = chargify.customers().create( customer );
  }

  @AfterClass
  public static void cleanup()
  {
    chargify.customers().deleteIfExists( customerUnderTest.getId() );
    chargify.customers().deleteIfExists( customerWithReferenceUnderTest.getId() );
  }

  @Test
  public void customerShouldBeFoundByValidId()
  {
    final Optional<Customer> customer = chargify.customers().findById( customerUnderTest.getId() );
    assertTrue( "Customer not found", customer.isPresent() );
  }

  @Test
  public void customerShouldNotBeFoundByInvalidId()
  {
    final Optional<Customer> customer = chargify.customers().findById( "nonexisting" );
    assertFalse( "Customer should not have been found", customer.isPresent() );
  }

  @Test
  public void findAllShouldFindAtLeastOne()
  {
    final List<Customer> customers = chargify.customers().findAll();
    assertTrue( "No customers found", customers.size() > 0 );
  }

  @Test
  public void customerShouldBeFoundByValidReference()
  {
    final Optional<Customer> customer = chargify.customers().findByReference( customerWithReferenceUnderTest.getReference() );
    assertTrue( "Customer not found by reference", customer.isPresent() );
  }

  @Test
  public void customerShouldNotBeFoundByInvalidReference()
  {
    final Optional<Customer> customer = chargify.customers().findByReference( "invalid" );
    assertFalse( "Customer should not have been found", customer.isPresent() );
  }
}
