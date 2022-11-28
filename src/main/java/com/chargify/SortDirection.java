package com.chargify;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortDirection
{
  ASC( "asc" ), DESC( "desc" );

  private final String value;
}
