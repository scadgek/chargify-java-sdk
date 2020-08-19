package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@JsonInclude( JsonInclude.Include.NON_NULL )
public class CreateSubscription implements Serializable
{

  @JsonProperty( "product_handle" )
  private String productHandle;

  @JsonProperty( "product_id" )
  private String productId;

  @JsonProperty( "product_price_point_handle" )
  private String productPricePointHandle;

  @JsonProperty( "product_price_point_id" )
  private String productPricePointId;

  @JsonProperty( "coupon_code" )
  private String couponCode;

  @JsonProperty( "payment_collection_method" )
  private String paymentCollectionMethod;

  @JsonProperty( "receives_invoice_emails" )
  private Boolean receivesInvoiceEmails;

  @JsonProperty( "net_terms" )
  private String netTerms;

  @JsonProperty( "customer_id" )
  private String customerId;

  @JsonProperty( "customer_reference" )
  private String customerReference;

  @JsonProperty( "next_billing_at" )
  private ZonedDateTime nextBillingAt;

  @JsonProperty( "stored_credential_transaction_id" )
  private Integer storedCredentialTransactionId;

  @JsonProperty( "sales_rep_id" )
  private Integer salesRepId;

  @JsonProperty( "payment_profile_id" )
  private String paymentProfileId;

  @JsonProperty( "ref" )
  private String reference;

  @JsonProperty( "customer_attributes" )
  private Customer customerAttributes;

  private List<SubscriptionComponent> components;

  private Map<String, String> metafields = new HashMap<>();
}
