package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude( JsonInclude.Include.NON_NULL )
public class ProductFamily
{
  private String id;

  private String name;

  private String description;

  private String handle;

  @JsonProperty( "accounting_code" )
  private String accountingCode;

  @JsonCreator
  public ProductFamily( @JsonProperty( "name" ) String name )
  {
    this.name = name;
  }

  public String getId()
  {
    return id;
  }

  public void setId( String id )
  {
    this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription( String description )
  {
    this.description = description;
  }

  public String getHandle()
  {
    return handle;
  }

  public void setHandle( String handle )
  {
    this.handle = handle;
  }

  public String getAccountingCode()
  {
    return accountingCode;
  }

  public void setAccountingCode( String accountingCode )
  {
    this.accountingCode = accountingCode;
  }

  @Override
  public String toString()
  {
    return "productFamily{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", handle='" + handle + '\'' +
            ", accountingCode='" + accountingCode + '\'' +
            '}';
  }
}
