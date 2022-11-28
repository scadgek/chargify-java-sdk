package com.chargify.model.wrappers;

import com.chargify.model.Transaction;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public final class TransactionWrapper
{
  private final Transaction transaction;

  @JsonCreator
  public TransactionWrapper( @JsonProperty( "transaction" ) Transaction transaction )
  {
    this.transaction = transaction;
  }
}
