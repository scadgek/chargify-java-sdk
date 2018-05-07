package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

@JsonInclude( JsonInclude.Include.NON_NULL )
public class Product
{
  private String id;

  private String name;

  private String handle;

  private String description;

  @JsonProperty( "accounting_code" )
  private String accountingCode;

  @JsonProperty( "request_credit_card" )
  private Boolean requestCreditCard;

  @JsonProperty( "expiration_interval" )
  private String expirationInterval;

  @JsonProperty( "expiration_interval_unit" )
  private String expirationIntervalUnit;

  @JsonProperty( "created_at" )
  private Date createdAt;

  @JsonProperty( "updated_at" )
  private Date updatedAt;

  @JsonProperty( "price_in_cents" )
  private Integer priceInCents;

  private String interval;

  @JsonProperty( "interval_unit" )
  private String intervalUnit;

  @JsonProperty( "initial_charge_in_cents" )
  private String initialChargeInCents;

  @JsonProperty( "trial_price_in_cents" )
  private Integer trialPriceInCents;

  @JsonProperty( "trial_interval" )
  private Integer trialInterval;

  @JsonProperty( "trial_interval_unit" )
  private String trialIntervalUnit;

  @JsonProperty( "archived_at" )
  private Date archivedAt;

  @JsonProperty( "require_credit_card" )
  private Boolean requireCreditCard;

  @JsonProperty( "return_params" )
  private String returnParams;

  private Boolean taxable;

  @JsonProperty( "update_return_url" )
  private String updateReturnUrl;

  @JsonProperty( "initial_charge_after_trial" )
  private Boolean initialChargeAfterTrial;

  @JsonProperty( "version_number" )
  private Integer versionNumber;

  @JsonProperty( "update_return_params" )
  private String updateReturnParams;

  @JsonProperty( "product_family" )
  private ProductFamily productFamily;

  @JsonProperty( "public_signup_pages" )
  private List<PublicSignupPage> publicSignupPages;

  public String getId()
  {
    return id;
  }

  public void setId( String id )
  {
    this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public String getHandle()
  {
    return handle;
  }

  public void setHandle( String handle )
  {
    this.handle = handle;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription( String description )
  {
    this.description = description;
  }

  public String getAccountingCode()
  {
    return accountingCode;
  }

  public void setAccountingCode( String accountingCode )
  {
    this.accountingCode = accountingCode;
  }

  public Boolean getRequestCreditCard()
  {
    return requestCreditCard;
  }

  public void setRequestCreditCard( Boolean requestCreditCard )
  {
    this.requestCreditCard = requestCreditCard;
  }

  public String getExpirationInterval()
  {
    return expirationInterval;
  }

  public void setExpirationInterval( String expirationInterval )
  {
    this.expirationInterval = expirationInterval;
  }

  public String getExpirationIntervalUnit()
  {
    return expirationIntervalUnit;
  }

  public void setExpirationIntervalUnit( String expirationIntervalUnit )
  {
    this.expirationIntervalUnit = expirationIntervalUnit;
  }

  public Date getCreatedAt()
  {
    return createdAt;
  }

  public void setCreatedAt( Date createdAt )
  {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt()
  {
    return updatedAt;
  }

  public void setUpdatedAt( Date updatedAt )
  {
    this.updatedAt = updatedAt;
  }

  public Integer getPriceInCents()
  {
    return priceInCents;
  }

  public void setPriceInCents( Integer priceInCents )
  {
    this.priceInCents = priceInCents;
  }

  public String getInterval()
  {
    return interval;
  }

  public void setInterval( String interval )
  {
    this.interval = interval;
  }

  public String getIntervalUnit()
  {
    return intervalUnit;
  }

  public void setIntervalUnit( String intervalUnit )
  {
    this.intervalUnit = intervalUnit;
  }

  public String getInitialChargeInCents()
  {
    return initialChargeInCents;
  }

  public void setInitialChargeInCents( String initialChargeInCents )
  {
    this.initialChargeInCents = initialChargeInCents;
  }

  public Integer getTrialInterval()
  {
    return trialInterval;
  }

  public void setTrialInterval( Integer trialInterval )
  {
    this.trialInterval = trialInterval;
  }

  public String getTrialIntervalUnit()
  {
    return trialIntervalUnit;
  }

  public void setTrialIntervalUnit( String trialIntervalUnit )
  {
    this.trialIntervalUnit = trialIntervalUnit;
  }

  public Date getArchivedAt()
  {
    return archivedAt;
  }

  public void setArchivedAt( Date archivedAt )
  {
    this.archivedAt = archivedAt;
  }

  public Boolean getRequireCreditCard()
  {
    return requireCreditCard;
  }

  public void setRequireCreditCard( Boolean requireCreditCard )
  {
    this.requireCreditCard = requireCreditCard;
  }

  public String getReturnParams()
  {
    return returnParams;
  }

  public void setReturnParams( String returnParams )
  {
    this.returnParams = returnParams;
  }

  public Boolean getTaxable()
  {
    return taxable;
  }

  public void setTaxable( Boolean taxable )
  {
    this.taxable = taxable;
  }

  public String getUpdateReturnUrl()
  {
    return updateReturnUrl;
  }

  public void setUpdateReturnUrl( String updateReturnUrl )
  {
    this.updateReturnUrl = updateReturnUrl;
  }

  public Boolean getInitialChargeAfterTrial()
  {
    return initialChargeAfterTrial;
  }

  public void setInitialChargeAfterTrial( Boolean initialChargeAfterTrial )
  {
    this.initialChargeAfterTrial = initialChargeAfterTrial;
  }

  public Integer getVersionNumber()
  {
    return versionNumber;
  }

  public void setVersionNumber( Integer versionNumber )
  {
    this.versionNumber = versionNumber;
  }

  public String getUpdateReturnParams()
  {
    return updateReturnParams;
  }

  public void setUpdateReturnParams( String updateReturnParams )
  {
    this.updateReturnParams = updateReturnParams;
  }

  public ProductFamily getProductFamily()
  {
    return productFamily;
  }

  public void setProductFamily( ProductFamily productFamily )
  {
    this.productFamily = productFamily;
  }

  public List<PublicSignupPage> getPublicSignupPages()
  {
    return publicSignupPages;
  }

  public void setPublicSignupPages( List<PublicSignupPage> publicSignupPages )
  {
    this.publicSignupPages = publicSignupPages;
  }

  public Integer getTrialPriceInCents()
  {
    return trialPriceInCents;
  }

  public void setTrialPriceInCents( Integer trialPriceInCents )
  {
    this.trialPriceInCents = trialPriceInCents;
  }

  @Override
  public String toString()
  {
    return "Product{" + "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", handle='" + handle + '\'' +
            ", description='" + description + '\'' +
            ", accountingCode='" + accountingCode + '\'' +
            ", requestCreditCard=" + requestCreditCard +
            ", expirationInterval='" + expirationInterval + '\'' +
            ", expirationIntervalUnit='" + expirationIntervalUnit + '\'' +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            ", priceInCents=" + priceInCents +
            ", interval='" + interval + '\'' +
            ", intervalUnit='" + intervalUnit + '\'' +
            ", initialChargeInCents='" + initialChargeInCents + '\'' +
            ", trialPriceInCents=" + trialPriceInCents +
            ", trialInterval=" + trialInterval +
            ", trialIntervalUnit='" + trialIntervalUnit + '\'' +
            ", archivedAt=" + archivedAt +
            ", requireCreditCard=" + requireCreditCard +
            ", returnParams='" + returnParams + '\'' +
            ", taxable=" + taxable +
            ", updateReturnUrl='" + updateReturnUrl + '\'' +
            ", initialChargeAfterTrial=" + initialChargeAfterTrial +
            ", versionNumber=" + versionNumber +
            ", updateReturnParams='" + updateReturnParams + '\'' +
            ", productFamily=" + productFamily +
            ", publicSignupPages=" + publicSignupPages +
            '}';
  }
}
