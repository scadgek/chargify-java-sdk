package com.chargify.model.wrappers;

import com.chargify.model.RenewalPreview;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class RenewalPreviewWrapper
{
  private final RenewalPreview renewalPreview;

  @JsonCreator
  public RenewalPreviewWrapper( @JsonProperty( "renewal_preview" ) RenewalPreview renewalPreview )
  {
    this.renewalPreview = renewalPreview;
  }

  public RenewalPreview getRenewalPreview()
  {
    return renewalPreview;
  }
}
