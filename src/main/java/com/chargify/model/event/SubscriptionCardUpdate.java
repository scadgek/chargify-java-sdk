package com.chargify.model.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties( ignoreUnknown = true )
public class SubscriptionCardUpdate
{
    @JsonProperty( "event_id" )
    private String eventId;
    private Site site;
    private Subscription subscription;
    private Customer customer;
    @JsonProperty( "previous_payment_profile" )
    private PaymentProfile previousPaymentProfile;
    @JsonProperty( "updated_payment_profile" )
    private PaymentProfile updatedPaymentProfile;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties( ignoreUnknown = true )
    public static class Subscription
    {
        private Long id;
        private String state;
        @JsonProperty( "balance_in_cents" )
        private Long balanceInCents;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties( ignoreUnknown = true )
    public static class Customer
    {
        private Long id;
        @JsonProperty( "first_name" )
        private String firstName;
        @JsonProperty( "last_name" )
        private String lastName;
        private String reference;
        private String organization;
        private String email;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties( ignoreUnknown = true )
    public static class PaymentProfile
    {
        private Long id;
        @JsonProperty( "first_name" )
        private String firstName;
        @JsonProperty( "last_name" )
        private String lastName;
        @JsonProperty( "masked_card_number" )
        private String maskedCardNumber;
        @JsonProperty( "card_type" )
        private String cardType;
        @JsonProperty( "expiration_month" )
        private Integer expirationMonth;
        @JsonProperty( "expiration_year" )
        private Integer expirationYear;
        @JsonProperty( "current_vault" )
        private String currentVault;
        @JsonProperty( "vault_token" )
        private String vaultToken;
        @JsonProperty( "customer_vault_token" )
        private String customerVaultToken;
        @JsonProperty( "billing_address" )
        private String billingAddress;
        @JsonProperty( "billing_address_2" )
        private String billingAddress2;
        @JsonProperty( "billing_city" )
        private String billingCity;
        @JsonProperty( "billing_state" )
        private String billingState;
        @JsonProperty( "billing_zip" )
        private String billingZip;
        @JsonProperty( "billing_country" )
        private String billingCountry;
    }
}
