package com.chargify.model.wrappers;

import com.chargify.model.Allocation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class AllocationWrapper
{
  private final Allocation allocation;

  @JsonCreator
  public AllocationWrapper( @JsonProperty( "allocation" ) Allocation wrappedAllocation )
  {
    this.allocation = wrappedAllocation;
  }

  public Allocation getAllocation()
  {
    return allocation;
  }
}
