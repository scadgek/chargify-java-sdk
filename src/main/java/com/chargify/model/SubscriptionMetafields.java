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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude( JsonInclude.Include.NON_NULL )
public class SubscriptionMetafields
{
  @JsonProperty( "application_id" )
  private String applicationId;

  @JsonProperty( "application_name" )
  private String applicationName;

  @JsonProperty( "developers_emails" )
  private String developersEmails;

  @JsonProperty( "subscription_date" )
  private String subscriptionDate;

  public String getApplicationId()
  {
    return applicationId;
  }

  public void setApplicationId( String applicationId )
  {
    this.applicationId = applicationId;
  }

  public String getApplicationName()
  {
    return applicationName;
  }

  public void setApplicationName( String applicationName )
  {
    this.applicationName = applicationName;
  }

  public String getDevelopersEmails()
  {
    return developersEmails;
  }

  public void setDevelopersEmails( String developersEmails )
  {
    this.developersEmails = developersEmails;
  }

  public String getSubscriptionDate()
  {
    return subscriptionDate;
  }

  public void setSubscriptionDate( String subscriptionDate )
  {
    this.subscriptionDate = subscriptionDate;
  }
}
