speed
```
Map<String, List<ReconOrderPairDTO>> pairedOrdersByTranche = pairedOrdersForDeal.stream()
    .peek(pair -> {
        // Only update pairs for the current deal
        if (pair.getDealId().equals(ecmDealDTO.getId())) {
            // Get the tranche id from either the Brio order or the External order
            String trancheId = Optional.ofNullable(pair.getBrioOrder())
                                    .orElse(pair.getExternalOrder())
                                    .getTrancheId();

            // Find the matching tranche in the deal
            ecmDealDTO.getTranches().stream()
                .filter(tranche -> tranche.getId().equals(trancheId))
                .findFirst()
                .ifPresent(matchingTranche -> {
                    // Get the list of syndicate members for the matching tranche
                    List<SyndicateMember> syndicateMembers = matchingTranche.getSyndicateMembers();

                    // Iterate through the list to aggregate the boolean values.
                    // For example, if any member is GS, we mark the pair as GS.
                    boolean isGs = syndicateMembers.stream()
                                     .anyMatch(member -> member.isGs());
                    boolean isSettlementAgent = syndicateMembers.stream()
                                               .anyMatch(member -> member.isSettlementAgent());

                    pair.setIsGs(isGs);
                    pair.setIsSettlementAgent(isSettlementAgent);
                });
        }
    })
    .collect(Collectors.groupingBy(pair ->
         Optional.ofNullable(pair.getBrioOrder())
                 .orElse(pair.getExternalOrder())
                 .getTrancheId()));
```
