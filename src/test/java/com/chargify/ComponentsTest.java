package com.chargify;

import com.chargify.model.Component;
import com.chargify.model.ComponentKind;
import com.chargify.model.ProductFamily;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ComponentsTest extends ChargifyTest
{
  private static ProductFamily productFamily;

  @BeforeClass
  public static void setup()
  {
    productFamily = chargify.createProductFamily( new ProductFamily( randomName() ) );
  }

  @Test
  public void createQuantityBasedComponent()
  {
    final Component component = new Component();
    component.setKind( ComponentKind.quantity_based_component );
    component.setName( randomName() );
    component.setUnitName( randomName() );
    component.setPricingScheme( "per_unit" );
    component.setUnitPrice( 10. );
    final Component createdComponent = chargify.createComponent( productFamily.getId(), component );
    assertEquals( "Wrong component kind", ComponentKind.quantity_based_component, createdComponent.getKind() );
  }

  @Test
  public void createMeteredComponent()
  {
    final Component component = new Component();
    component.setKind( ComponentKind.metered_component );
    component.setName( randomName() );
    component.setUnitName( randomName() );
    component.setPricingScheme( "per_unit" );
    component.setUnitPrice( 10. );
    final Component createdComponent = chargify.createComponent( productFamily.getId(), component );
    assertEquals( "Wrong component kind", ComponentKind.metered_component, createdComponent.getKind() );
  }

  @Test
  public void createOnOffComponent()
  {
    final Component component = new Component();
    component.setKind( ComponentKind.on_off_component );
    component.setName( randomName() );
    component.setUnitPrice( 10. );
    final Component createdComponent = chargify.createComponent( productFamily.getId(), component );
    assertEquals( "Wrong component kind", ComponentKind.on_off_component, createdComponent.getKind() );
  }

  @AfterClass
  public static void cleanup()
  {
    chargify.archiveProductFamilyById( productFamily.getId() );
  }
}
