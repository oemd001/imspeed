# imspeed

if (orderReconDealReport.getOrderReconTrancheReports() != null) {
    for (OrderReconTrancheReportDTO trancheReport : orderReconDealReport.getOrderReconTrancheReports()) {
        // Check if orderBookSummaries is not null or empty
        if (trancheReport.getOrderBookSummaries() != null && !trancheReport.getOrderBookSummaries().isEmpty()) {
            for (Map.Entry<SourceSystem, OrderBookSummaryDTO> entry : trancheReport.getOrderBookSummaries().entrySet()) {
                // Check if the key matches ECM_CMG
                if ("ECM_CMG".equals(entry.getKey().toString())) { // Assuming SourceSystem has a proper `toString()` or is a String
                    // Extract the OrderBookSummaryDTO
                    OrderBookSummaryDTO summary = entry.getValue();
                    if (summary != null) {
                        BigDecimal totalDemand = summary.getTotalDemand();
                        System.out.println("SourceSystem: " + entry.getKey());
                        System.out.println("Total Demand: " + totalDemand);
                    }
                }
            }
        } else {
            System.out.println("No orderBookSummaries available for this tranche report.");
        }
    }
} else {
    System.out.println("No tranche reports available.");
}
