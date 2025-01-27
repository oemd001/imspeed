# imspeed

```
BigDecimal ecmCmgTotalDemand = BigDecimal.ZERO;

if (orderReconDealReport.getOrderReconTrancheReports() != null) {
    for (OrderReconTrancheReportDTO trancheReport : orderReconDealReport.getOrderReconTrancheReports()) {
        if (trancheReport.getOrderBookSummaries() != null && !trancheReport.getOrderBookSummaries().isEmpty()) {
            for (Map.Entry<SourceSystem, OrderBookSummaryDTO> entry : trancheReport.getOrderBookSummaries().entrySet()) {
                if ("ECM_CMG".equals(entry.getKey().toString())) {
                    OrderBookSummaryDTO summary = entry.getValue();
                    if (summary != null && summary.getTotalDemand() != null) {
                        ecmCmgTotalDemand = ecmCmgTotalDemand.add(summary.getTotalDemand());
                    }
                }
            }
        }
    }
}

System.out.println("Total Demand for ECM_CMG: " + ecmCmgTotalDemand);
```
