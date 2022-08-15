package com.chargify.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.io.InputStream;

public final class ChargifyResponseErrorHandler extends DefaultResponseErrorHandler
{
  private static final ObjectMapper objectMapper = new ObjectMapper();

  public static void handleError( int statusCode, String strBody )
  {
    if( statusCode >= 200 && statusCode < 300 )
      return;

    if( statusCode >= 300 && statusCode < 400 )
    {
      if( statusCode == HttpStatus.NOT_FOUND.value() )
        throw new ResourceNotFoundException();
      else if( statusCode == HttpStatus.FORBIDDEN.value() ) // TODO: see issue https://chargify.zendesk.com/hc/en-us/requests/69553
        throw new ChargifyException( strBody );
      else
      {
        try
        {
          throw objectMapper.readValue( strBody, ChargifyError.class ).exception();
        }
        catch( JsonProcessingException e )
        {
          throw new RuntimeException( e );
        }
      }
    }
    else if( statusCode >= 400 && statusCode < 500 )
    {
      throw new HttpServerErrorException( statusCode, strBody );
    }
    else
    {
      throw new UnknownHttpStatusCodeException( statusCode, strBody );
    }
  }

  @Override
  protected void handleError( ClientHttpResponse response, HttpStatus statusCode ) throws IOException
  {
    switch( statusCode.series() )
    {
      case CLIENT_ERROR:
        if( statusCode == HttpStatus.NOT_FOUND )
          throw new ResourceNotFoundException();
        else if( statusCode == HttpStatus.FORBIDDEN ) // TODO: see issue https://chargify.zendesk.com/hc/en-us/requests/69553
          throw new ChargifyException( readInputStream( response.getBody() ) );
        else
          throw objectMapper.readValue( response.getBody(), ChargifyError.class ).exception();
      case SERVER_ERROR:
        throw new HttpServerErrorException( statusCode.value(), readInputStream( response.getBody() ) );
      default:
        throw new UnknownHttpStatusCodeException( statusCode.value(), readInputStream( response.getBody() ) );
    }
  }

  private String readInputStream( final InputStream stream )
  {
    return new java.util.Scanner( stream ).useDelimiter( "\\A" ).next();
  }
}
