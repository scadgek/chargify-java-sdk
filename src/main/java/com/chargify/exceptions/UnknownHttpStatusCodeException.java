package com.chargify.exceptions;

import lombok.Getter;

@Getter
public class UnknownHttpStatusCodeException extends RuntimeException
{
  private final int statusCode;
  private final String responseBody;

  public UnknownHttpStatusCodeException( int statusCode, String responseBody )
  {
    super( "Unknown status code [" + statusCode + "] Body: " + responseBody );
    this.statusCode = statusCode;
    this.responseBody = responseBody;
  }
}
