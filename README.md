# imspeed
```
List<CompletableFuture<OrderReconDTO>> futures = dealIds.stream()
    .map(dealId -> CompletableFuture.supplyAsync(() -> {
        try {
            // Make the API call
            Response<OrderReconDTO> response = httpExecutor.executeCall(
                ecmCMGIntegrationService.getExternalTrancheIdForDeal(dealId.toString())
            );

            // Log the raw API response for debugging
            System.out.println("Raw response for dealId " + dealId + ": " + response);

            // Process the response
            if (response.isSuccessful() && response.body() != null) {
                return response.body(); // Return the body as OrderReconDTO
            }
        } catch (Exception e) {
            // Log or handle the exception
            System.err.println("Error fetching data for dealId: " + dealId + " - " + e.getMessage());
        }

        return null; // Return null if there's an issue
    }))
    .collect(Collectors.toList()); // Collect CompletableFuture<OrderReconDTO> into a List

// Wait for all futures to complete and collect the results
List<OrderReconDTO> orderReconDTOList = futures.stream()
    .map(CompletableFuture::join) // Wait for each future to complete
    .filter(Objects::nonNull) // Exclude null responses
    .collect(Collectors.toList()); // Collect into a list

// Create a HashMap to map dealId to externalId
Map<Integer, String> dealIdToExternalIdMap = orderReconDTOList.stream()
    .filter(dto -> dto.getDealId() != null && dto.getExternalId() != null) // Ensure non-null values
    .collect(Collectors.toMap(
        OrderReconDTO::getDealId, // Use dealId as the key
        OrderReconDTO::getExternalId // Use externalId as the value
    ));

// Iterate through the response body and add the correct externalId
if (response.isSuccessful() && response.body() != null) {
    List<OrderReconDTO> mergedList = response.body().stream()
        .peek(dto -> {
            // Map the externalId using dealId
            if (dto.getDealId() != null && dealIdToExternalIdMap.containsKey(dto.getDealId())) {
                dto.setExternalId(dealIdToExternalIdMap.get(dto.getDealId()));
            }
        })
        .collect(Collectors.toList());

    // Print the final merged list for debugging
    System.out.println("Final merged list: " + mergedList);
}```
