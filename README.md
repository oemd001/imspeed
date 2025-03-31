speed
```
else if (reconOrderPairDTO.getExternalOrder() != null
        && reconOrderPairDTO.getExternalOrder().getMismatchedAcknowledgements() != null
        && reconOrderPairDTO.getBrioOrder() != null) {

    // Get the brioID from the BrioOrder
    String brioId = reconOrderPairDTO.getBrioOrder().getBrioID();

    // Only proceed if brioId is not null
    if (brioId != null) {
        // Check if the brioId exists among the mismatched acknowledgements
        boolean brioIdFound = reconOrderPairDTO
            .getExternalOrder()
            .getMismatchedAcknowledgements()
            .stream()
            .filter(Objects::nonNull)               // skip any null acknowledgements
            .map(IndicationAcknowledgementWebDto::getBrioID)
            .filter(Objects::nonNull)               // skip any null brioIDs
            .anyMatch(id -> brioId.equals(id));     // compare safely

        if (brioIdFound) {
            // Handle the case where the brioID was found in the list
            // e.g., return true, set a flag, etc.
        } else {
            // Handle the case where the brioID was not found
        }
    }
}
```
