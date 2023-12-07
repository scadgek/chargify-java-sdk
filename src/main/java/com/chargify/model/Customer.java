package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Data
public class Customer implements Serializable
{
  @JsonProperty( "first_name" )
  private String firstName;
  @JsonProperty( "last_name" )
  private String lastName;
  @JsonProperty( "email" )
  private String email;
  @JsonProperty( "cc_emails" )
  private String ccEmails;

  private String organization;

  private String reference;

  private String id;

  @JsonProperty( "created_at" )
  private Date createdAt;

  @JsonProperty( "updated_at" )
  private Date updatedAt;

  private String address;

  @JsonProperty( "address_2" )
  private String address2;

  private String city;

  private String state;

  private String zip;

  private String country;

  private String phone;

  private Boolean verified;

  @JsonProperty( "portal_customer_created_at" )
  private Date portalCustomerCreatedAt;

  @JsonProperty( "portal_invite_last_sent_at" )
  private Date portalInviteLastSentAt;

  @JsonProperty( "portal_invite_last_accepted_at" )
  private Date portalInviteLastAcceptedAt;

  @JsonCreator
  public Customer( @JsonProperty( "first_name" ) String firstName,
                   @JsonProperty( "last_name" ) String lastName,
                   @JsonProperty( "email" ) String email )
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }
}
