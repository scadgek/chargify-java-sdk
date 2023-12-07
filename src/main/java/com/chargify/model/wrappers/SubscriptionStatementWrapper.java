package com.chargify.model.wrappers;

import com.chargify.model.SubscriptionStatement;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public final class SubscriptionStatementWrapper
{
  private final SubscriptionStatement statement;

  @JsonCreator
  public SubscriptionStatementWrapper( @JsonProperty( "statement" ) SubscriptionStatement statement )
  {
    this.statement = statement;
  }
}
