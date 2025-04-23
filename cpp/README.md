```
if (
    // 1) If either order object is null, skip into the block
    eitherIsNull(brioOrder, externalOrder)
    
    // 2) Or if the demand types don’t match
    || !valuesAreEqual(
         brioOrder.getDemandType(),
         externalOrder != null
           ? externalOrder.getDemandType()
           : null
       )
    
    // 3) Or, if we have a non-null externalOrder whose source is ECM_CMG
    //    and the brioOrder’s demandType is "POT"
    || (
         externalOrder != null
      && externalOrder.getReconSource() == SourceSystem.ECM_CMG
      && "POT".equals(brioOrder.getDemandType())
       )
) {
    // …your logic here
}
```
