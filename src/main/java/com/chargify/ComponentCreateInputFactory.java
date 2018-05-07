package com.chargify;

import com.chargify.model.ComponentCreateInput;
import com.chargify.model.OnOffComponentCreateInput;
import com.chargify.model.QuantityBasedComponentCreateInput;
import org.springframework.stereotype.Component;

@Component
public class ComponentCreateInputFactory
{
  public ComponentCreateInput create( boolean itemizable )
  {
    return itemizable ? new QuantityBasedComponentCreateInput() : new OnOffComponentCreateInput();
  }
}
