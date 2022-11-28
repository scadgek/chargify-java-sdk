package com.chargify;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class SubscriptionTransactionsSearchOptions
{
  @Builder.Default
  private SortDirection direction = SortDirection.DESC;
  private List<String> kinds;
  private Integer maxId;
  @Builder.Default
  private int page = 1;
  @Builder.Default
  private int pageSize = 20;
  private LocalDate sinceDate;
  private Integer sinceId;
  private LocalDate untilDate;

  public SubscriptionTransactionsSearchOptions nextPage()
  {
    page++;
    return this;
  }

  public SubscriptionTransactionsSearchOptions previousPage()
  {
    if( (page - 1) < 1 )
      throw new IllegalStateException( "Page can't be less than 1" );
    page--;
    return this;
  }
}
