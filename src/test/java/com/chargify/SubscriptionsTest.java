package com.chargify;

import com.chargify.model.SubscriptionOutput;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Optional;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestToUriTemplate;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class SubscriptionsTest
{
  private final String apiKey = "testapikey";
  private final String domain = "testdomain";
  private final RestTemplate restTemplate = new RestTemplateBuilder().build();
  private final MockRestServiceServer mockServer = MockRestServiceServer.createServer( restTemplate );
  private final Chargify chargify = new ChargifyService( domain, apiKey, restTemplate );

  @Test
  public void subscriptionRetrieved()
  {
    final String subscriptionIdUnderTest = MockResponses.READ_SUBSCRIPTION_BY_ID_SUBSCRIPTION_ID;

    mockServer.expect( requestToUriTemplate( "https://" + domain + ".chargify.com" + Endpoints.READ_SUBSCRIPTION_BY_ID( subscriptionIdUnderTest ) ) )
            .andExpect( header( "Authorization", "Basic " + Base64.getEncoder().encodeToString( (apiKey + ":x").getBytes() ) ) )
            .andRespond( withSuccess( MockResponses.READ_SUBSCRIPTION_BY_ID, MediaType.APPLICATION_JSON ) );

    final Optional<SubscriptionOutput> maybeSubscription = chargify.readSubscription( subscriptionIdUnderTest );

    Assert.assertTrue( "Subscription not found", maybeSubscription.isPresent() );
    Assert.assertEquals( "Invalid subscription state", "active", maybeSubscription.get().getState() );
  }
}
