# imspeed
```
List<CompletableFuture<Response<List<DynamicMapping>>>> futures = dealIds.stream()
            .map(dealId -> CompletableFuture.supplyAsync(() -> {
                try {
                    // Simulate an API call
                    Response<List<DynamicMapping>> response = httpExecutor.executeCall(
                        ecmCMGIntegrationService.getDynamicMappingsForDeal(dealId.toString())
                    );

                    // Log the raw API response
                    System.out.println("Raw response for dealId " + dealId + ": " + response);

                    // Return the API response
                    return response;
                } catch (Exception e) {
                    System.err.println("Error fetching data for dealId: " + dealId + " - " + e.getMessage());
                }
                return null; // Return null if there's an issue
            }))
            .collect(Collectors.toList());

        // Step 2: Process each CompletableFuture and extract the mappings for "Tranche"
        List<DynamicMapping> trancheMappings = futures.stream()
            .map(CompletableFuture::join) // Wait for each future to complete
            .filter(Objects::nonNull) // Exclude null responses
            .flatMap(response -> {
                if (response.isSuccessful() && response.body() != null) {
                    // Filter mappings for "Tranche"
                    return response.body().stream()
                        .filter(mapping -> "Tranche".equalsIgnoreCase(mapping.getDynamicMappingType()));
                }
                return Stream.empty(); // If the response is unsuccessful, return an empty stream
            })
            .collect(Collectors.toList());

        // Step 3: Extract the cmgExternalCode values for the filtered tranche mappings
        List<String> trancheExternalCodes = trancheMappings.stream()
            .map(DynamicMapping::getCmgExternalCode) // Extract cmgExternalCode
            .filter(Objects::nonNull) // Ensure no null values are added
            .collect(Collectors.toList());

        // Print the final results
        System.out.println("Filtered Tranche Mappings: " + trancheMappings);
        System.out.println("Filtered Tranche External Codes: " + trancheExternalCodes);
}```
