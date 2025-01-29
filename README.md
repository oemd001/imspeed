# imspeed
```Java
// Step 1: Use CompletableFuture to fetch data for each dealId
List<CompletableFuture<Response<?>>> futures = dealIds.stream()
    .map(dealId -> CompletableFuture.supplyAsync(() -> {
        try {
            // Make the API call
            Response<?> response = httpExecutor.executeCall(
                ecmCmgIntegrationService.getExternalTrancheIdForDeal(dealId.toString())
            );

            // Log the raw API response
            System.out.println("Raw response for dealId " + dealId + ": " + response.body());

            return response; // Return the raw response
        } catch (Exception e) {
            System.err.println("Error fetching data for dealId: " + dealId + " - " + e.getMessage());
        }
        return null; // Return null if there's an issue
    }))
    .collect(Collectors.toList());

// Step 2: Collect all successful responses and handle both cases
List<OrderReconDTO> orderReconDTOList = new ArrayList<>();

for (CompletableFuture<Response<?>> future : futures) {
    Response<?> response = future.join(); // Wait for completion

    if (response != null && response.isSuccessful() && response.body() != null) {
        Object body = response.body();

        if (body instanceof OrderReconDTO) {
            orderReconDTOList.add((OrderReconDTO) body); // Single object case
        } else if (body instanceof List<?>) {
            // Handle case where API returns List<OrderReconDTO>
            List<?> responseList = (List<?>) body;
            for (Object obj : responseList) {
                if (obj instanceof OrderReconDTO) {
                    orderReconDTOList.add((OrderReconDTO) obj);
                }
            }
        } else {
            System.err.println("Unexpected response type: " + body.getClass().getName());
        }
    } else {
        System.err.println("Warning: Skipped a response because it was null or unsuccessful.");
    }
}

// Step 3: Extract the cmgExternalCode where dynamicMappingType is "TRANCHE"
Map<Integer, String> dealIdToTrancheCodeMap = orderReconDTOList.stream()
    .filter(dto -> dto.getDynamicMappings() != null) // Ensure dynamicMappings is not null
    .flatMap(dto -> dto.getDynamicMappings().stream()) // Flatten the dynamicMappings list
    .filter(mapping -> "TRANCHE".equalsIgnoreCase(mapping.getDynamicMappingType())) // Filter for "TRANCHE"
    .collect(Collectors.toMap(
        DynamicMapping::getEcmDealId,  // Use ecmDealId as the key
        DynamicMapping::getCmgExternalCode, // Use cmgExternalCode as the value
        (existing, replacement) -> existing // Keep the first occurrence in case of duplicates
    ));

// Step 4: Merge the extracted Tranche codes into OrderReconDTOs
orderReconDTOList.forEach(dto -> {
    if (dto.getEcmDealId() != null && dealIdToTrancheCodeMap.containsKey(dto.getEcmDealId())) {
        dto.setExternalTrancheId(dealIdToTrancheCodeMap.get(dto.getEcmDealId()));
    }
});

// Step 5: Print the final merged list for debugging
System.out.println("Final OrderReconDTO list with Tranche External IDs: " + orderReconDTOList);
```
