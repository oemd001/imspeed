speed
```
OrderMismatchType compareAllocationDesignations(
    OrderReconDTO brioOrder, OrderReconDTO externalOrder) {

    boolean isMismatch = !valuesAreEqual(brioOrder.getDesignations(), externalOrder.getDesignations());

    if (isMismatch) {
        if ("ECM_CMG".equals(brioOrder.getSourceSystem())) {
            return OrderMismatchType.BRIO_INDICATION_MISMATCH;
        } else {
            return OrderMismatchType.BRIO_ECM_ALLOCATION_DESIGNATION_OUT_OF_SYNC;
        }
    }

    return OrderMismatchType.NONE;
}
```
