# imspeed
```
List<CompletableFuture<OrderReconDTO>> futures = dealIds.stream()
    .map(dealId -> CompletableFuture.supplyAsync(() -> {
        try {
            // Make the API call
            Response<OrderReconDTO> response = httpExecutor.executeCall(
                ecmCMGIntegrationService.getExternalTrancheIdForDeal(dealId.toString())
            );

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
```
