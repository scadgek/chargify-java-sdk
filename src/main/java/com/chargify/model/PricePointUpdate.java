package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonInclude( JsonInclude.Include.NON_NULL )
@NoArgsConstructor
@AllArgsConstructor
@Data
public final class PricePointUpdate implements Serializable
{
  private String name;
  private Price[] prices;
}
