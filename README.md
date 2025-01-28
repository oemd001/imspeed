# imspeed

```
List<OrderReconDTO> vals = EcmDaoUtils.mergeAllocationAndIndicationDTOC(
    new ArrayList<>(), // Handle allocationResponse being null by passing an empty list
    indicationResponse != null && indicationResponse.body != null
        ? indicationResponse.body
        : new ArrayList<>(),
    misMatchedBrioIds, 
    SourceSystem.ECM_IPREO, 
    deals
);
```
