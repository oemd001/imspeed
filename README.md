if (lastModifiedDateTime == null 
    || Duration.between(lastModifiedDateTime, LocalDateTime.now(ZoneOffset.UTC)).toMinutes() > 1) {
    
    orderMismatchType = OrderMismatchType.BRIO_INDICATION_MISMATCH;
} else {
    orderMismatchType = OrderMismatchType.UPDATED_WITHIN_THRESHOLD;
}

return orderMismatchType;
