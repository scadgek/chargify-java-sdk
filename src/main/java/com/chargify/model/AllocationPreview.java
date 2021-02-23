package com.chargify.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
@JsonInclude( JsonInclude.Include.NON_NULL )
public class AllocationPreview
{
  @JsonProperty( "start_date" )
  private LocalDateTime startDate;
  @JsonProperty( "end_date" )
  private LocalDateTime endDate;
  @JsonProperty( "period_type" )
  private String periodType;
  @JsonProperty( "total_in_cents" )
  private Integer totalInCents;
  @JsonProperty( "total_discount_in_cents" )
  private Integer totalDiscountInCents;
  @JsonProperty( "total_tax_in_cents" )
  private Integer totalTaxInCents;
  @JsonProperty( "subtotal_in_cents" )
  private Integer subtotalInCents;
  @JsonProperty( "existing_balance_in_cents" )
  private Integer existingBalanceInCents;
  @JsonProperty( "direction" )
  private String direction;
  @JsonProperty( "proration_scheme" )
  private String prorationScheme;
  @JsonProperty( "line_items" )
  private List<LineItem> lineItems;
  @JsonProperty( "allocations" )
  private List<Allocation> allocations;


  @NoArgsConstructor
  @Data
  @JsonInclude( JsonInclude.Include.NON_NULL )
  public static class LineItem
  {
    @JsonProperty( "transaction_type" )
    private String transactionType;
    private ComponentKind kind;
    @JsonProperty( "amount_in_cents" )
    private Integer amountInCents;
    private String memo;
    @JsonProperty( "discount_amount_in_cents" )
    private Integer discountAmountInCents;
    @JsonProperty( "taxable_amount_in_cents" )
    private Integer taxable_amount_in_cents;
    @JsonProperty( "component_id" )
    private Integer componentId;
    @JsonProperty( "component_handle" )
    private String componentHandle;
    @JsonProperty( "accrue_charge" )
    private Boolean accrueCharge;
    @JsonProperty( "upgrade_charge" )
    private String upgradeCharge;
  }

  @NoArgsConstructor
  @Data
  @JsonInclude( JsonInclude.Include.NON_NULL )
  public static class Allocation
  {
    @JsonProperty( "allocation_id" )
    private String allocationId;
    @JsonProperty( "component_id" )
    private Integer componentId;
    @JsonProperty( "subscription_id" )
    private Integer subscriptionId;
    private Integer quantity;
    @JsonProperty( "previous_quantity" )
    private Integer previousQuantity;
    private String memo;
    private LocalDateTime timestamp;
    @JsonProperty( "proration_upgrade_scheme" )
    private String prorationUpgradeScheme;
    @JsonProperty( "proration_downgrade_scheme" )
    private String prorationDowngradeScheme;
    @JsonProperty( "price_point_id" )
    private Integer pricePointId;
    @JsonProperty( "previous_price_point_id" )
    private Integer previousPricePointId;
    @JsonProperty( "component_handle" )
    private String componentHandle;
    @JsonProperty( "accrue_charge" )
    private Boolean accrueCharge;
    @JsonProperty( "upgrade_charge" )
    private String upgradeCharge;
    @JsonProperty( "downgrade_credit" )
    private String downgradeCredit;
    @JsonProperty( "created_at" )
    private LocalDateTime createdAt;
  }

  @AllArgsConstructor
  @Getter
  public static class ComponentAllocationDTO
  {
    @JsonProperty( "component_id" )
    private final int componentId;
    private final int quantity;
  }
}
