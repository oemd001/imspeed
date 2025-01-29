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

        // Step 2: Collect all successful responses
        List<OrderReconDTO> orderReconDTOList = futures.stream()
            .map(CompletableFuture::join) // Wait for each future to complete
            .filter(Objects::nonNull) // Exclude null responses
            .flatMap(response -> response.body().stream()) // Flatten List<OrderReconDTO>
            .collect(Collectors.toList());

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
    }

```
