# imspeed
```Java
List<CompletableFuture<Response<List<OrderReconDTO>>>> futures = dealIds.stream()
            .map(dealId -> CompletableFuture.supplyAsync(() -> {
                try {
                    // Make the API call
                    Response<List<OrderReconDTO>> response = httpExecutor.executeCall(
                        ecmCmgIntegrationService.getExternalTrancheIdForDeal(dealId.toString())
                    );

                    // Log the raw API response
                    System.out.println("Raw response for dealId " + dealId + ": " + response.body());

                    // Return the response if successful
                    return response.isSuccessful() ? response : null;
                } catch (Exception e) {
                    System.err.println("Error fetching data for dealId: " + dealId + " - " + e.getMessage());
                }
                return null; // Return null if there's an issue
            }))
            .collect(Collectors.toList());
```
