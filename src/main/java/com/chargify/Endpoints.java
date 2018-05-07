package com.chargify;

final class Endpoints
{
  static String CREATE_ALLOCATION( final String subscriptionId, final String componentId )
  {
    return "/subscriptions/" + subscriptionId + "/components/" + componentId + "/allocations.json";
  }

  static final String CREATE_CUSTOMER = "/customers.json";

  static String READ_PRODUCT_VIA_API_HANDLE( final String apiHandle )
  {
    return "/products/handle/" + apiHandle + ".json";
  }

  static String READ_CUSTOMER_BY_REFERENCE( final String reference )
  {
    return "/customers/lookup.json?reference=" + reference;
  }

  static String LIST_SUBSCRIPTIONS_BY_CUSTOMER( final String customerId )
  {
    return "/customers/" + customerId + "/subscriptions.json";
  }

  static String READ_SUBSCRIPTION_BY_ID( final String subscriptionId )
  {
    return "/subscriptions/" + subscriptionId + ".json";
  }

  static String LIST_SUBSCRIPTIONS_BY_STATE( final String state, final int page, final int pageSize )
  {
    return "/subscriptions.json?page=" + page + "&per_page=" + pageSize + "&state=" + state;
  }

  static String CREATE_MIGRATION( final String subscriptionId )
  {
    return "/subscriptions/" + subscriptionId + "/migrations.json";
  }

  static String LIST_COMPONENTS_BY_SUBSCRIPTION( final String subscriptionId )
  {
    return "/subscriptions/" + subscriptionId + "/components.json";
  }

  static String RENEWAL_PREVIEW( final String subscriptionId )
  {
    return "/subscriptions/" + subscriptionId + "/renewals/preview.json";
  }
}
