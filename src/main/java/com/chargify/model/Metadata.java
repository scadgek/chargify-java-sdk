package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties( ignoreUnknown = true )
@JsonInclude( JsonInclude.Include.NON_NULL )
public class Metadata implements Serializable
{
  private String value;

  @JsonProperty( "resource_id" )
  private String resourceId;

  private String name;

  @JsonProperty( "current_name" )
  private String currentName;

  public String getValue()
  {
    return value;
  }

  public void setValue( String value )
  {
    this.value = value;
  }

  public String getResourceId()
  {
    return resourceId;
  }

  public void setResourceId( String resourceId )
  {
    this.resourceId = resourceId;
  }

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  @Override
  public String toString()
  {
    return "Metadata{" +
            "value='" + value + '\'' +
            ", resourceId='" + resourceId + '\'' +
            ", name='" + name + '\'' +
            '}';
  }

  public String getCurrentName()
  {
    return currentName;
  }

  public void setCurrentName( String currentName )
  {
    this.currentName = currentName;
  }
}
