package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude( JsonInclude.Include.NON_NULL )
public class ComponentWithPricePoints implements Serializable
{
  private Component component;
  private Set<PricePoint> pricePoints;
}
