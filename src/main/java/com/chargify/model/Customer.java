/*
 * ********************************************************************************************************************
 *  <p/>
 *  BACKENDLESS.COM CONFIDENTIAL
 *  <p/>
 *  ********************************************************************************************************************
 *  <p/>
 *  Copyright 2012 BACKENDLESS.COM. All Rights Reserved.
 *  <p/>
 *  NOTICE:  All information contained herein is, and remains the property of Backendless.com and its suppliers,
 *  if any.  The intellectual and technical concepts contained herein are proprietary to Backendless.com and its
 *  suppliers and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret
 *  or copyright law. Dissemination of this information or reproduction of this material is strictly forbidden
 *  unless prior written permission is obtained from Backendless.com.
 *  <p/>
 *  ********************************************************************************************************************
 */

package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonInclude( JsonInclude.Include.NON_NULL )
public class Customer
{
  private String firstName;
  private String lastName;
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

  public Customer()
  {

  }

  @JsonProperty( "first_name" )
  public String getFirstName()
  {
    return firstName;
  }

  @JsonProperty( "last_name" )
  public String getLastName()
  {
    return lastName;
  }

  @JsonProperty( "email" )
  public String getEmail()
  {
    return email;
  }

  public String getCcEmails()
  {
    return ccEmails;
  }

  public void setCcEmails( String ccEmails )
  {
    this.ccEmails = ccEmails;
  }

  public String getOrganization()
  {
    return organization;
  }

  public void setOrganization( String organization )
  {
    this.organization = organization;
  }

  public String getReference()
  {
    return reference;
  }

  public void setReference( String reference )
  {
    this.reference = reference;
  }

  public String getId()
  {
    return id;
  }

  public void setId( String id )
  {
    this.id = id;
  }

  public Date getCreatedAt()
  {
    return createdAt;
  }

  public void setCreatedAt( Date createdAt )
  {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt()
  {
    return updatedAt;
  }

  public void setUpdatedAt( Date updatedAt )
  {
    this.updatedAt = updatedAt;
  }

  public String getAddress()
  {
    return address;
  }

  public void setAddress( String address )
  {
    this.address = address;
  }

  public String getAddress2()
  {
    return address2;
  }

  public void setAddress2( String address2 )
  {
    this.address2 = address2;
  }

  public String getCity()
  {
    return city;
  }

  public void setCity( String city )
  {
    this.city = city;
  }

  public String getState()
  {
    return state;
  }

  public void setState( String state )
  {
    this.state = state;
  }

  public String getZip()
  {
    return zip;
  }

  public void setZip( String zip )
  {
    this.zip = zip;
  }

  public String getCountry()
  {
    return country;
  }

  public void setCountry( String country )
  {
    this.country = country;
  }

  public String getPhone()
  {
    return phone;
  }

  public void setPhone( String phone )
  {
    this.phone = phone;
  }

  public Boolean getVerified()
  {
    return verified;
  }

  public void setVerified( Boolean verified )
  {
    this.verified = verified;
  }

  public Date getPortalCustomerCreatedAt()
  {
    return portalCustomerCreatedAt;
  }

  public void setPortalCustomerCreatedAt( Date portalCustomerCreatedAt )
  {
    this.portalCustomerCreatedAt = portalCustomerCreatedAt;
  }

  public Date getPortalInviteLastSentAt()
  {
    return portalInviteLastSentAt;
  }

  public void setPortalInviteLastSentAt( Date portalInviteLastSentAt )
  {
    this.portalInviteLastSentAt = portalInviteLastSentAt;
  }

  public Date getPortalInviteLastAcceptedAt()
  {
    return portalInviteLastAcceptedAt;
  }

  public void setPortalInviteLastAcceptedAt( Date portalInviteLastAcceptedAt )
  {
    this.portalInviteLastAcceptedAt = portalInviteLastAcceptedAt;
  }

  @Override
  public String toString()
  {
    return "Customer{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", ccEmails='" + ccEmails + '\'' +
            ", organization='" + organization + '\'' +
            ", reference='" + reference + '\'' +
            ", id='" + id + '\'' +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            ", address='" + address + '\'' +
            ", address2='" + address2 + '\'' +
            ", city='" + city + '\'' +
            ", state='" + state + '\'' +
            ", zip='" + zip + '\'' +
            ", country='" + country + '\'' +
            ", phone='" + phone + '\'' +
            ", verified=" + verified +
            ", portalCustomerCreatedAt=" + portalCustomerCreatedAt +
            ", portalInviteLastSentAt=" + portalInviteLastSentAt +
            ", portalInviteLastAcceptedAt=" + portalInviteLastAcceptedAt +
            '}';
  }
}
