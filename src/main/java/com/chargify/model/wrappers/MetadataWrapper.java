package com.chargify.model.wrappers;

import com.chargify.model.Metadata;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class MetadataWrapper
{
  private final Metadata[] metadata;

  @JsonCreator
  public MetadataWrapper( @JsonProperty( "metadata" ) Metadata[] wrappedMetadata )
  {
    this.metadata = wrappedMetadata;
  }

  public Metadata[] getMetadata()
  {
    return metadata;
  }
}
