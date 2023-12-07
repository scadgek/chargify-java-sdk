package com.chargify.model.wrappers;

import com.chargify.model.Migration;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public final class MigrationWrapper
{
  private final Migration migration;

  @JsonCreator
  public MigrationWrapper( @JsonProperty( "migration" ) Migration wrappedMigration )
  {
    this.migration = wrappedMigration;
  }
}
