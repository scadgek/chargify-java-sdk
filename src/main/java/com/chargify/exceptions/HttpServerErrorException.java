package com.chargify.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpServerErrorException extends RuntimeException
{
  private final int statusCode;
  private final String responseBody;

  public HttpServerErrorException( int statusCode, String responseBody )
  {
    super( HttpStatus.resolve( statusCode ).getReasonPhrase() + ". Body: " + responseBody );
    this.statusCode = statusCode;
    this.responseBody = responseBody;
  }
}
