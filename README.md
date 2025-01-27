# imspeed

BigDecimal totalDemandSum = BigDecimal.ZERO;

if (orderReconDealReport.getOrderReconTrancheReports() != null) {
    for (OrderReconTrancheReportDTO trancheReport : orderReconDealReport.getOrderReconTrancheReports()) {
        if (trancheReport.getOrderBookSummaries() != null && !trancheReport.getOrderBookSummaries().isEmpty()) {
            for (OrderBookSummaryDTO summary : trancheReport.getOrderBookSummaries().values()) {
                if (summary != null && summary.getTotalDemand() != null) {
                    totalDemandSum = totalDemandSum.add(summary.getTotalDemand());
                }
            }
        }
    }
}

System.out.println("Total Demand Sum: " + totalDemandSum);
