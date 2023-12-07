package com.chargify.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;

public final class ChargifyResponseErrorHandler extends DefaultResponseErrorHandler
{
  private static final ObjectMapper objectMapper = new ObjectMapper();

  static {
    objectMapper.disable( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS );
    objectMapper.configure( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.registerModules( new JavaTimeModule() );
  }

  public static void handleError( int statusCode, String strBody )
  {
    if( statusCode >= 200 && statusCode < 300 )
      return;

    if( statusCode >= 400 && statusCode < 500 )
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
    else if( statusCode >= 500 && statusCode < 600 )
    {
      throw new HttpServerErrorException( statusCode, strBody );
    }
    else
    {
      throw new UnknownHttpStatusCodeException( statusCode, strBody );
    }
  }

  public static WebClient.ResponseSpec handleError( WebClient.ResponseSpec response )
  {
    response.onStatus( HttpStatusCode::is4xxClientError, clientResponse -> {
      HttpStatusCode statusCode = clientResponse.statusCode();
      if( statusCode == HttpStatus.NOT_FOUND )
        return Mono.error( new ResourceNotFoundException() );
      else if( statusCode == HttpStatus.FORBIDDEN ) // TODO: see issue https://chargify.zendesk.com/hc/en-us/requests/69553
        return clientResponse.bodyToMono( String.class ).map( ChargifyException::new ).flatMap( Mono::error );
      else
      {
        return clientResponse.bodyToMono( String.class )
            .<ChargifyException>handle( ( body, sink ) -> {
              try
              {
                sink.next( objectMapper.readValue( body, ChargifyError.class ).exception() );
              }
              catch( JsonProcessingException e )
              {
                sink.error( new RuntimeException( e ) );
              }
            } )
            .flatMap( Mono::error );
      }
    } );

    response.onStatus(
            HttpStatusCode::is5xxServerError,
            clientResponse -> clientResponse.bodyToMono( String.class )
                    .map( message -> new HttpServerErrorException( clientResponse.statusCode().value(), message ) )
                    .flatMap( Mono::error ) );

    response.onStatus(
            HttpStatusCode::isError,
            clientResponse -> response.bodyToMono( String.class )
                    .map( message -> new UnknownHttpStatusCodeException( clientResponse.statusCode().value(), message ) )
                    .flatMap( Mono::error ) );

    return response;
  }

  @Override
  protected void handleError( ClientHttpResponse response, HttpStatusCode statusCode ) throws IOException
  {
    if( statusCode.is4xxClientError() )
    {
      if( statusCode == HttpStatus.NOT_FOUND )
        throw new ResourceNotFoundException();
      else if( statusCode == HttpStatus.FORBIDDEN ) // TODO: see issue https://chargify.zendesk.com/hc/en-us/requests/69553
        throw new ChargifyException( readInputStream( response.getBody() ) );
      else
        throw objectMapper.readValue( response.getBody(), ChargifyError.class ).exception();
    }
    else if( statusCode.is5xxServerError() )
      throw new HttpServerErrorException( statusCode.value(), readInputStream( response.getBody() ) );
    else
      throw new UnknownHttpStatusCodeException( statusCode.value(), readInputStream( response.getBody() ) );
  }

  private String readInputStream( final InputStream stream )
  {
    return new java.util.Scanner( stream ).useDelimiter( "\\A" ).next();
  }
}
