package com.chargify;

public final class MockResponses
{
  public static final String READ_SUBSCRIPTION_BY_ID_SUBSCRIPTION_ID = "21000969";
  public static final String READ_SUBSCRIPTION_BY_ID = "{\n" +
          "  \"subscription\": {\n" +
          "    \"id\": " + READ_SUBSCRIPTION_BY_ID_SUBSCRIPTION_ID + ",\n" +
          "    \"state\": \"active\",\n" +
          "    \"trial_started_at\": null,\n" +
          "    \"trial_ended_at\": null,\n" +
          "    \"activated_at\": \"2018-02-27T14:13:30-06:00\",\n" +
          "    \"created_at\": \"2018-02-27T14:13:30-06:00\",\n" +
          "    \"updated_at\": \"2018-02-27T14:13:30-06:00\",\n" +
          "    \"expires_at\": null,\n" +
          "    \"balance_in_cents\": 0,\n" +
          "    \"current_period_ends_at\": \"2018-03-27T15:13:30-05:00\",\n" +
          "    \"next_assessment_at\": \"2018-03-27T15:13:30-05:00\",\n" +
          "    \"canceled_at\": null,\n" +
          "    \"cancellation_message\": null,\n" +
          "    \"next_product_id\": null,\n" +
          "    \"cancel_at_end_of_period\": false,\n" +
          "    \"payment_collection_method\": \"automatic\",\n" +
          "    \"snap_day\": null,\n" +
          "    \"cancellation_method\": null,\n" +
          "    \"current_period_started_at\": \"2018-02-27T14:13:30-06:00\",\n" +
          "    \"previous_state\": \"active\",\n" +
          "    \"signup_payment_id\": 228415132,\n" +
          "    \"signup_revenue\": \"1000.00\",\n" +
          "    \"delayed_cancel_at\": null,\n" +
          "    \"coupon_code\": null,\n" +
          "    \"total_revenue_in_cents\": 100000,\n" +
          "    \"product_price_in_cents\": 100000,\n" +
          "    \"product_version_number\": 1,\n" +
          "    \"payment_type\": \"credit_card\",\n" +
          "    \"referral_code\": \"kszx2g\",\n" +
          "    \"coupon_use_count\": null,\n" +
          "    \"coupon_uses_allowed\": null,\n" +
          "    \"reason_code\": null,\n" +
          "    \"automatically_resume_at\": null,\n" +
          "    \"coupon_codes\": [],\n" +
          "    \"customer\": {\n" +
          "      \"id\": 20613561,\n" +
          "      \"first_name\": \"Alysa\",\n" +
          "      \"last_name\": \"Test\",\n" +
          "      \"organization\": null,\n" +
          "      \"email\": \"alysa@example.com\",\n" +
          "      \"created_at\": \"2018-02-27T14:13:30-06:00\",\n" +
          "      \"updated_at\": \"2018-02-27T14:13:31-06:00\",\n" +
          "      \"reference\": null,\n" +
          "      \"address\": null,\n" +
          "      \"address_2\": null,\n" +
          "      \"city\": null,\n" +
          "      \"state\": null,\n" +
          "      \"zip\": null,\n" +
          "      \"country\": null,\n" +
          "      \"phone\": null,\n" +
          "      \"portal_invite_last_sent_at\": \"2018-02-27T14:13:31-06:00\",\n" +
          "      \"portal_invite_last_accepted_at\": null,\n" +
          "      \"verified\": false,\n" +
          "      \"portal_customer_created_at\": \"2018-02-27T14:13:31-06:00\",\n" +
          "      \"cc_emails\": null,\n" +
          "      \"tax_exempt\": false\n" +
          "    },\n" +
          "    \"product\": {\n" +
          "      \"id\": 4581816,\n" +
          "      \"name\": \"Basic\",\n" +
          "      \"handle\": \"basic\",\n" +
          "      \"description\": \"\",\n" +
          "      \"accounting_code\": \"\",\n" +
          "      \"request_credit_card\": true,\n" +
          "      \"expiration_interval\": null,\n" +
          "      \"expiration_interval_unit\": \"never\",\n" +
          "      \"created_at\": \"2017-11-02T15:00:11-05:00\",\n" +
          "      \"updated_at\": \"2017-11-07T09:20:12-06:00\",\n" +
          "      \"price_in_cents\": 100000,\n" +
          "      \"interval\": 1,\n" +
          "      \"interval_unit\": \"month\",\n" +
          "      \"initial_charge_in_cents\": null,\n" +
          "      \"trial_price_in_cents\": null,\n" +
          "      \"trial_interval\": null,\n" +
          "      \"trial_interval_unit\": \"month\",\n" +
          "      \"archived_at\": null,\n" +
          "      \"require_credit_card\": true,\n" +
          "      \"return_params\": \"\",\n" +
          "      \"taxable\": false,\n" +
          "      \"update_return_url\": \"\",\n" +
          "      \"tax_code\": \"\",\n" +
          "      \"initial_charge_after_trial\": false,\n" +
          "      \"version_number\": 1,\n" +
          "      \"update_return_params\": \"\",\n" +
          "      \"product_family\": {\n" +
          "        \"id\": 1025627,\n" +
          "        \"name\": \"Acme Products\",\n" +
          "        \"description\": \"\",\n" +
          "        \"handle\": \"acme-products\",\n" +
          "        \"accounting_code\": null\n" +
          "      },\n" +
          "      \"public_signup_pages\": [\n" +
          "        {\n" +
          "          \"id\": 333589,\n" +
          "          \"return_url\": \"\",\n" +
          "          \"return_params\": \"\",\n" +
          "          \"url\": \"https://general-goods.chargifypay.com/subscribe/hbwtd98j3hk2/basic\"\n" +
          "        },\n" +
          "        {\n" +
          "          \"id\": 335926,\n" +
          "          \"return_url\": \"\",\n" +
          "          \"return_params\": \"\",\n" +
          "          \"url\": \"https://general-goods.chargifypay.com/subscribe/g366zy67c7rm/basic\"\n" +
          "        }\n" +
          "      ]\n" +
          "    },\n" +
          "    \"credit_card\": {\n" +
          "      \"id\": 14345100,\n" +
          "      \"first_name\": \"Alysa\",\n" +
          "      \"last_name\": \"Test\",\n" +
          "      \"masked_card_number\": \"XXXX-XXXX-XXXX-1\",\n" +
          "      \"card_type\": \"bogus\",\n" +
          "      \"expiration_month\": 10,\n" +
          "      \"expiration_year\": 2020,\n" +
          "      \"customer_id\": 20613561,\n" +
          "      \"current_vault\": \"bogus\",\n" +
          "      \"vault_token\": \"1\",\n" +
          "      \"billing_address\": null,\n" +
          "      \"billing_city\": null,\n" +
          "      \"billing_state\": null,\n" +
          "      \"billing_zip\": null,\n" +
          "      \"billing_country\": null,\n" +
          "      \"customer_vault_token\": null,\n" +
          "      \"billing_address_2\": null,\n" +
          "      \"payment_type\": \"credit_card\"\n" +
          "    }\n" +
          "  }\n" +
          "}";
}
