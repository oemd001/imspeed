# Action Plan | Migrating **TFWeb** to **Playwright**

---

## Overview
| Item | Details |
|------|---------|
| **Objective** | Replace TFWeb with Playwright for UI E2E tests while keeping everything inside **`brio-e2e-automated-tests`** |
| **Finding** | TFWeb tests live in their own repo; swapping the runner has zero impact on product code |
| **Strategy** | Incremental migration—suite‑by‑suite—until Playwright reaches 100 % parity |

---

## Step&nbsp;0 – Feasibility

> **Question:** Can we migrate TFWeb to Playwright and keep tests in the current repository?  
> **Answer:** **Yes.** The isolation of the TFWeb test bundle means we can drop‐in Playwright with no downstream risk.

*Replace the existing **SyndicateLinkUI‑brio** TFWeb harness with an equivalent Playwright harness.*

---

## Step&nbsp;1 – Configuration Parity

Create a Playwright‑based `tfweb.conf.js` replacement that supports multiple environments and suites.

| Requirement | Notes |
|-------------|-------|
| **Environments** | `local`, `dev`, `qa` |
| **Suites** | `americasIPO`, `americasSingleTrancheIPO`, `americasCmgGsLeadSingleTrancheFO`, etc. |
| **Browser** | Chrome (headless/headful) |
| **Auth** | GSSSO token injection |
| **Tip** | Use **Playwright Projects** in `playwright.config.ts` to keep env‑specific settings tidy |

---

## Step&nbsp;2 – Test Inventory & Migration Status

_Update the **Status** column as work progresses (`Pending → In‑Progress → Complete`)._

| Section | Test Name | File Name | Status |
|---------|-----------|-----------|--------|
| Deal Setup | dealCreate | func/DealSetup/DealCreateSpec.js | Pending |
| Deal Setup | dealSearch | func/DealSetup/DealSearchSpec.js | Pending |
| Deal Setup | recentDeals | func/DealSetup/RecentDealsSpec.js | Pending |
| Deal Setup | activeDeals | func/DealSetup/ActiveDealsSpec.js | Pending |
| Pre‑Pricing | dealInformation | func/PrePricing/DealInformationSpec.js | Pending |
| Pre‑Pricing | currencies | func/PrePricing/CurrenciesSpec.js | Pending |
| Pre‑Pricing | instruments | func/PrePricing/InstrumentsSpec.js | Pending |
| Pre‑Pricing | tranches | func/PrePricing/TranchesSpec.js | Pending |
| Pre‑Pricing | dealerbook | func/PrePricing/DealerbookSpec.js | Pending |
| Pre‑Pricing | prePricingContacts | func/PrePricing/ContactsSpec.js | Pending |
| Pre‑Pricing | launchDeal | func/PrePricing/LaunchDealSpec.js | Pending |
| Pre‑Pricing | ioiEntry | func/PrePricing/IoiEntryConfigurationSpec.js | Pending |
| Checklist | checklistOnDealCreation | func/Checklist/ChecklistOnDealCreationSpec.js | Pending |
| Staleness Notifications | stalenessNotifications | func/StalenessNotifications/StalenessNotificationsSpec.js | Pending |
| Pricing | pricingDealInformation | func/Pricing/DealInformationSpec.js | Pending |
| Pricing | dealLayout | func/Pricing/DealLayoutSpec.js | Pending |
| Pricing | pricingContacts | func/Pricing/ContactsSpec.js | Pending |
| Pricing | releasePricingTerms | func/Pricing/PricingSpec.js | Pending |
| Trade Booking Setup | tranchesSection | func/TradeBookingSetup/TranchesSpec.js | Pending |
| Trade Booking Setup | chargesAndStampTaxSection | func/TradeBookingSetup/ChargesAndStampTaxSpec.js | Pending |
| Trade Booking Setup | taxesAndChargesModal | func/TradeBookingSetup/TaxesAndChargesModalSpec.js | Pending |
| Trade Booking Setup | instrumentsGrid | func/TradeBookingSetup/InstrumentsSpec.js | Pending |
| Trade Booking Setup | stabilizationDailyTools | func/TradeBookingSetup/StabilizationsSpec.js | Pending |
| Pre‑Pricing | unsavedChanges | func/PrePricing/UnsavedChangesSpec.js | Pending |
| Trade Booking | tradeBookingSetupIndications | func/TradeBooking/TBSetupSpec.js | Pending |
| Trade Booking | ioGridSpec | func/TradeBooking/IOGridSpec.js | Pending |
| Trade Booking | underwritingGridSpec | func/TradeBooking/UnderwritingGridSpec.js | Pending |
| Trade Booking | tradesGridSpec | func/TradeBooking/TradesGridSpec.js | Pending |
| Trade Booking | summaryGridSpec | func/TradeBooking/SummaryGridSpec.js | Pending |
| Email | prePricingEmail | func/Email/PrePricingEmailSpec.js | Pending |
| Email | tradeBookingSetupEmail | func/Email/TradeBookingSetupEmailSpec.js | Pending |
| Email | panEmail | func/Email/PanEmailSpec.js | Pending |
| Pricing Sheet | pricingSheet | func/PricingSheet/PricingSheetSpec.js | Pending |
| Final Settlement | standardExpenses | func/FinalSettlement/StandardExpensesSpec.js | Pending |
| Final Settlement | additionalExpenses | func/FinalSettlement/AdditionalExpensesSpec.js | Pending |
| Final Settlement | fsReports | func/FinalSettlement/ReportsSpec.js | Pending |
| Final Settlement | oopAdjustments | func/FinalSettlement/PaymentSubmissionSpec.js | Pending |
| Final Settlement | additionalAdjustments | func/FinalSettlement/AdditionalAdjustmentsSpec.js | Pending |
| Final Settlement | paymentSubmission | func/FinalSettlement/PaymentSubmissionSpec.js | Pending |
| Final Settlement | validationExpenses | func/FinalSettlement/ExpensesValidationSpec.js | Pending |
| TearDown | tearDown | func/TearDown/TearDown.js | Pending |
| Setup | americasIPOSetup | func/americasIPO.js | Pending |
| Setup | americasSingleTrancheIPOSetup | func/americasSingleTrancheIPO.js | Pending |
| Setup | americasBlockTests | func/americasBlockTrade.js | Pending |
| Setup | australiaBlockTests | func/australiaBlockTrade.js | Pending |
| Setup | australiaIPOSetup | func/australiaIPO.js | Pending |
| Multi‑Region | followOnTests | func/MultiRegionTests/followOn.js | Pending |
| Multi‑Region | japanIPOTests | func/MultiRegionTests/japanIPO.js | Pending |
| Multi‑Region | emeaIPOTests | func/MultiRegionTests/emeaIPO.js | Pending |
| Multi‑Region | auctionValidations | func/PrePricing/auctionValidationsSpec.js | Pending |
| CMG Mapping | americasCmgFO | func/americasCmgFO.js | Pending |
| CMG Mapping | CmgSingleTrancheGsLeadBrokerSpec | func/prePricing/CmgSingleTrancheGsLeadBrokerSpec.js | Pending |
| CMG Mapping | CmgSingleTrancheGsNonLeadBrokerSpec | func/prePricing/CmgSingleTrancheGsNonLeadBrokerSpec.js | Pending |
| CMG Mapping | publishDealToCmg | func/CmgMapping/CmgMappingPublishSpec.js | Pending |
| CMG Mapping | linkDealToCmgDeal | func/CmgMapping/CmgMappingLinkSpec.js | Pending |

---

## Step&nbsp;3 – Migration Automation
1. **Bootstrap** a Playwright project: `npx playwright init`  
2. **Port utilities** to Playwright fixtures/helpers.  
3. **CI**: swap the TFWeb test step for `npx playwright test`.  
4. **Gradual rollout**: keep TFWeb in CI until Playwright reaches full coverage.

---
