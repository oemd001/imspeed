speed
```
Map<String, List<ReconOrderPairDTO>> pairedOrdersByTranche = new HashMap<>();
try {
    // Assume that ecmDealDTO.getTranches() returns a List of tranches,
    // and each tranche's getSyndicateMembers() returns a List<List<SyndicateMemberDTO>>.
    boolean overallFlag = false;
    
    // Iterate over tranches and their syndicate members.
    outer:
    for (TrancheDTO tranche : ecmDealDTO.getTranches()) {
        for (List<SyndicateMemberDTO> syndicateList : tranche.getSyndicateMembers()) {
            for (SyndicateMemberDTO member : syndicateList) {
                // Check if both flags are true.
                if (member.isGs() && member.isSettlementAgent()) {
                    overallFlag = true;
                    break outer;  // Found a match; exit all loops.
                }
            }
        }
    }
    
    // Process the list of order pairs and update the flags based on the overallFlag.
    pairedOrdersByTranche = pairedOrdersForDeal.stream()
        .peek(pair -> {
            pair.setIsGs(overallFlag);
            pair.setIsSettlementAgent(overallFlag);
        })
        .collect(Collectors.groupingBy(pair -> 
            Optional.ofNullable(pair.getBrioOrder())
                    .orElse(pair.getExternalOrder())
                    .getTrancheId()
        ));
} catch (Exception ex) {
    ex.printStackTrace();
}```
