# imspeed
```Java
public void populateMismatchCounts(OrderReconDealReportDTO dealReport,
                                   Map<Integer, Collection<ReconOrderPairDTO>> reconPairsPerTranche) {

    // For each TrancheReport in the DealReport, find the mismatchCount and set it.
    for (OrderReconTrancheReportDTO trancheReport : dealReport.getOrderReconTrancheReports()) {

        // 1) Identify which trancheID we’re dealing with
        Integer trancheId = trancheReport.getTrancheId();
        if (trancheId == null) {
            continue; // or throw/log
        }

        // 2) Get the ReconOrderPairs for that Tranche
        Collection<ReconOrderPairDTO> pairs =
            reconPairsPerTranche.getOrDefault(trancheId, Collections.emptyList());

        // 3) Calculate a mismatch count (example logic)
        int mismatchCount = 0;
        for (ReconOrderPairDTO pair : pairs) {
            OrderReconDTO brioOrder = pair.getBrioOrder();
            if (brioOrder != null
                && brioOrder.getMismatchedAcknowledgements() != null
                && !brioOrder.getMismatchedAcknowledgements().isEmpty()) {
                mismatchCount++;
            }
        }

        // 4) Store that mismatchCount in the OrderBookSummaryDTO
        //    (Create or update an existing summary, whichever makes sense in your app)
        SourceSystem systemKey = SourceSystem.getBrioSourceSystemForExternalSystem(dealReport.getExternalSystem());
        OrderBookSummaryDTO summary = 
            trancheReport.getOrderBookSummaries().get(systemKey);

        if (summary == null) {
            summary = new OrderBookSummaryDTO();
        }
        summary.setNumberOfMisMatchedAcknowledgements(mismatchCount);

        trancheReport.getOrderBookSummaries().put(systemKey, summary);
    }
}
```
