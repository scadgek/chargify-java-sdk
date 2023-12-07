package com.chargify.model;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class SubscriptionReactivationData
{
  private String subscriptionId;
  private boolean includeTrial = false;
  private boolean preserveBalance = false;
  private String couponCode;
  private Boolean resume;
  private boolean forgiveBalance = false;
}
