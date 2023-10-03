package com.chargify.model.wrappers;

import com.chargify.model.Usage;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public final class UsageWrapper
{
  private final Usage usage;

  @JsonCreator
  public UsageWrapper( @JsonProperty( "usage" ) Usage wrappedUsage )
  {
    this.usage = wrappedUsage;
  }
}
