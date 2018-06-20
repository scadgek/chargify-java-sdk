package com.chargify;

import com.chargify.model.Customer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
    final Customer customer = chargify.findCustomerById( customerUnderTest.getId() );
    assertNotNull( "Customer not found", customer );
  }

  @Test
  public void customerShouldNotBeFoundByInvalidId()
  {
    final Customer customer = chargify.findCustomerById( "nonexisting" );
    assertNull( "Customer should not have been found", customer );
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
    final Customer customer = chargify.findCustomerByReference( customerWithReferenceUnderTest.getReference() );
    assertNotNull( "Customer not found by reference", customer );
  }

  @Test
  public void customerShouldNotBeFoundByInvalidReference()
  {
    final Customer customer = chargify.findCustomerByReference( "invalid" );
    assertNull( "Customer should not have been found", customer );
  }
}
