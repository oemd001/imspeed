# imspeed
# How to Trigger Recon When Brio and CMG Indications Are Mismatched

## Overview
Recon should be triggered when there is a discrepancy between indications from Brio and CMG. This guide outlines the step-by-step process to create this mismatch and ensure recon is triggered correctly.

---

## Steps to Create a Mismatch and Trigger Recon

### 1. Create a Deal on Syndicate Link
- Find an existing deal or create a new one.
- Ensure the deal is linked to CMG.

### 2. Launch the Deal
- Proceed with launching the deal to activate institutional orders.

### 3. Link the Deal with CMG
- Confirm that CMG is properly linked with the deal on Syndicate Link.

### 4. Ensure Institutional Orders (from RAPTOR) Are Selected
- Make sure that institutional orders are turned on in RAPTOR.

### 5. Send Orders in from RAPTOR
- Navigate to RAPTOR and send in institutional orders.

### 6. Modify Indications on Brio Side
- On the Brio (GS Broker Account) side:
  - Select the deal you’ve created.
  - Modify the indications for the order book in Brio.
  - Ensure that these indications differ from those in CMG.

### 7. Verify Mismatched Indications
- Cross-check indications in CMG and Brio to confirm the values do not align.

### 8. Trigger Recon
- At this stage, recon should automatically trigger due to the mismatch between Brio and CMG indications.
- If recon does not trigger, manually initiate it through the system interface.

---

## Expected Outcome
Once recon is triggered, the system should detect the mismatch and flag the discrepancy for resolution. This ensures that indications between Brio and CMG remain accurate and aligned.
