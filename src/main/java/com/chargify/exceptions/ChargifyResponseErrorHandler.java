package com.chargify.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import java.io.IOException;
import java.io.InputStream;

public final class ChargifyResponseErrorHandler extends DefaultResponseErrorHandler
{
  private ObjectMapper objectMapper = new ObjectMapper();

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
        throw new HttpServerErrorException( statusCode, response.getStatusText(),
                                            response.getHeaders(), getResponseBody( response ), getCharset( response ) );
      default:
        throw new UnknownHttpStatusCodeException( statusCode.value(), response.getStatusText(),
                                                  response.getHeaders(), getResponseBody( response ), getCharset( response ) );
    }
  }

  private String readInputStream( final InputStream stream )
  {
    return new java.util.Scanner( stream ).useDelimiter( "\\A" ).next();
  }
}
