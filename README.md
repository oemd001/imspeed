# imspeed
ec2-34-195-162-242.compute-1.amazonaws.com
```Java
public Optional<OrderBookSummaryDTO> getOrderBookSummary(
    Collection<OrderReconDTO> reconDTOs,
    SourceSystem externalSourceSystem
) {
    // Count only the non-cancelled, non-duplicate, active orders
    List<OrderReconDTO> filteredReconDTOs =
        reconDTOs.stream()
                 .filter(this::neitherIsCancelledOrDuplicateInactiveOrder)
                 .collect(Collectors.toList());

    // Count how many of those have a non-empty mismatchedAcknowledgements list
    long mismatchCount = filteredReconDTOs.stream()
        .filter(r -> r.getMismatchedAcknowledgements() != null
                  && !r.getMismatchedAcknowledgements().isEmpty())
        .count();

    final OrderBookSummaryDTO orderBookSummaryDTO = OrderBookSummaryDTO.builder()
        .numberOfOrders(filteredReconDTOs.size())
        .numberOfOrdersByGroup(getNumberOfOrdersPerGroup(filteredReconDTOs))
        .totalAllocation(getTotalAllocation(filteredReconDTOs, externalSourceSystem))
        .totalDemand(getTotalDemand(filteredReconDTOs))
        .numberOfMisMatchedAcknowledgements((int) mismatchCount) // <— Include your mismatch field here
        .build();

    return Optional.ofNullable(orderBookSummaryDTO);
}
```
