# imspeed
```Java
// For each tranche in the deal
for (EcmDealDTO.TrancheDTO tranche : deal.getTranches()) {
    try {
        // 1) Build the empty TrancheReport
        OrderReconTrancheReportDTO orderReconTrancheReport =
            OrderReconTrancheReportDTO.builder()
                .trancheId(tranche.getId())
                .trancheName(tranche.getName())
                .build();

        // 2) Grab all the order pairs for this tranche
        Collection<ReconOrderPairDTO> reconPairsForTranche =
            reconOrderPairsPerTranche.getOrDefault(tranche.getId(), Collections.emptyList());

        // 3) Count how many of those have a non-empty mismatchedAcknowledgements list
        int mismatchCount = 0;
        for (ReconOrderPairDTO pair : reconPairsForTranche) {
            OrderReconDTO brioOrder = pair.getBrioOrder(); 
            if (brioOrder != null) {
                List<IndicationAcknowledgementWebDto> mismatchedAcks =
                    brioOrder.getMismatchedAcknowledgements();

                // If the list is non-null and not empty, increment
                if (mismatchedAcks != null && !mismatchedAcks.isEmpty()) {
                    mismatchCount++;
                }
            }
        }

        // 4) Put that mismatchCount into the appropriate OrderBookSummaryDTO
        OrderBookSummaryDTO summary = new OrderBookSummaryDTO();
        summary.setNumberOfMisMatchedAcknowledgements(mismatchCount);

        // Choose the right source-system key:
        // For example, if 'externalSource' is the system we want to associate the summary with:
        orderReconTrancheReport.getOrderBookSummaries()
                               .put(SourceSystem.getBrioSourceSystemForExternalSystem(externalSource), summary);

        // 5) Do any other logic, like severity, mismatching orders, or calling services:
        setMismatchSeverityForTranche(orderReconTrancheReport, reconPairsForTranche);
        addMismatchingOrdersToReport(orderReconTrancheReport, groupReconOrderPairsByMismatchType(reconPairsForTranche));
        orderBookReconService.getResolutions(
            orderReconTrancheReport,
            groupReconOrderPairsByResolutionType(reconPairsForTranche)
        );

        // 6) Finally, add this TrancheReport to the overall DealReport
        orderReconDealReport.getOrderReconTrancheReports().add(orderReconTrancheReport);

    } catch (Exception e) {
        // handle/log exception
    }
}
```
