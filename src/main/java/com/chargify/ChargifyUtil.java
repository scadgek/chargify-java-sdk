package com.chargify;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ChargifyUtil
{
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern( "yyyy-MM-dd'T'HH:mm:ss'Z'" );

  public static String toChargifyDateString( LocalDateTime date )
  {
    return date.atZone( ZoneId.systemDefault() )
        .withZoneSameInstant( ZoneId.of( "UTC" ) )
        .toLocalDateTime()
        .format( DATE_FORMATTER );
  }
  public static String toChargifyDateString( ZonedDateTime date )
  {
    return date
        .withZoneSameInstant( ZoneId.of( "UTC" ) )
        .toLocalDateTime()
        .format( DATE_FORMATTER );
  }
}
