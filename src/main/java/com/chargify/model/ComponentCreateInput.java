package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude( JsonInclude.Include.NON_NULL )
public abstract class ComponentCreateInput
{
  private String name;
  private String description;

  public abstract ComponentCreateInput setPrice( Double price );

  public String getName()
  {
    return name;
  }

  public ComponentCreateInput setName( String name )
  {
    this.name = name;
    return this;
  }

  public String getDescription()
  {
    return description;
  }

  public ComponentCreateInput setDescription( String description )
  {
    this.description = description;
    return this;
  }
}
