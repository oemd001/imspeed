# imspeed
```Java
// Step 1: Fetch the orders response
final Response<List<OrderReconDTO>> getOrderResponseResponse = httpExecutor.executeCall(
    ecmCmgIntegrationService.getOrdersForDeals(dealIds)
);

// Step 2: Ensure response is valid before modifying
if (getOrderResponseResponse != null && getOrderResponseResponse.isSuccessful() 
    && getOrderResponseResponse.body() != null) {
    
    List<OrderReconDTO> orderReconDTOList = getOrderResponseResponse.body();

    // Step 3: Extract TRANCHE external IDs from dynamicMappings
    Map<Integer, String> dealIdToTrancheCodeMap = orderReconDTOList.stream()
        .filter(dto -> dto.getDynamicMappings() != null) // Ensure dynamicMappings is not null
        .flatMap(dto -> dto.getDynamicMappings().stream()) // Flatten the dynamicMappings list
        .filter(mapping -> "TRANCHE".equalsIgnoreCase(mapping.getDynamicMappingType())) // Filter for "TRANCHE"
        .collect(Collectors.toMap(
            DynamicMapping::getEcmDealId,  // Use ecmDealId as the key
            DynamicMapping::getCmgExternalCode, // Use cmgExternalCode as the value
            (existing, replacement) -> existing // Keep the first occurrence if duplicates exist
        ));

    // Step 4: Replace externalTrancheId for all OrderReconDTOs
    orderReconDTOList.forEach(dto -> {
        if (dto.getEcmDealId() != null && dealIdToTrancheCodeMap.containsKey(dto.getEcmDealId())) {
            dto.setExternalTrancheId(dealIdToTrancheCodeMap.get(dto.getEcmDealId()));
        }
    });

    // Step 5: Print the modified response to confirm replacements
    System.out.println("Updated OrderReconDTO list with Tranche External IDs: " + orderReconDTOList);
}
```
