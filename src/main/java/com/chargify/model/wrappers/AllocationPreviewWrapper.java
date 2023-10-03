package com.chargify.model.wrappers;

import com.chargify.model.AllocationPreview;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public final class AllocationPreviewWrapper
{
  private final AllocationPreview allocationPreview;

  @JsonCreator
  public AllocationPreviewWrapper( @JsonProperty( "allocation_preview" ) AllocationPreview allocationPreview )
  {
    this.allocationPreview = allocationPreview;
  }
}
